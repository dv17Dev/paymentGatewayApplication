package com.dev.payGwt.transaction;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dev.HdfcVer.userBal.HdfcUserBal;
import com.dev.payGwt.entity.PaymentEntity;
import com.dev.userVer.user.Usr;

/**
 * Rest Controller for the {@link Transactions} class.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@RestController
public class TransactionController {
	
	private static final String TRANSACTION_BANK_ERROR = "transaction.bankError";

	private static final String TRANSACTION_VENDOR_ERROR = "transaction.vendorError";

	private static final String TRANSACTION_NO_ERROR = "transaction.noError";

	private static final String TRANSACTION_SUCCESS = "transaction.success";

	private static final String HDFC_BALANCE_ERROR = "HDFC.balanceError";

	private static final String TRANSACTION_USER_ERROR = "transaction.userError";

	private static final String TRANSACTION_FAILURE = "transaction.failure";

	@Autowired
	private TransactionService transactionService;
	
	@Autowired 
	private PropertiesLoader propLoader;

	@Value("${user.url}")
	String userURL;
	
	@Value("${hdfc.url}")
	String hdfcURL;
	
	@Value("${hdfc.post}")
	String hdfcPostURL;

	@GetMapping("/transactions")
	public List<Transactions> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	@GetMapping("/transactions/users/{userId}")
	public List<Transactions> getTransactionByUserId(@PathVariable String userId) {
		return transactionService.getTransactionsByUserId(userId);
	}
	
	@GetMapping("/transactions/{txnId}")
	public Optional<Transactions> getTransactionById(@PathVariable String txnId) {
		return transactionService.getTransactionByTxnId(txnId);
	}

	@PostMapping("/paymentEntity")
	public String performTransaction(@RequestBody PaymentEntity paymentEntity) {

		Transactions txn = new Transactions(UUID.randomUUID().toString(), paymentEntity.getVendor(), paymentEntity.getBank(),
				paymentEntity.getPayMethod(), paymentEntity.getAmount(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		// Checking for user validity
		RestTemplate restTemplate = new RestTemplate();
		String id = paymentEntity.getUserId();
		
		Usr user = restTemplate.getForObject(userURL + id, Usr.class);
		if (user.getId() == null) {
			// User does not exist
			txn.setStatusCode(propLoader.getProperty(TRANSACTION_FAILURE));
			txn.setStatusMessage(propLoader.getProperty(TRANSACTION_USER_ERROR));
			txn.setUserId(null); txn.setUserName(null);
		} else {
			// User exists
			txn.setUserId(user.getId()); txn.setUserName(user.getName());
			// Checking if a valid bank is requested
			if (GatewayConstants.BANK_SBI.equalsIgnoreCase(paymentEntity.getBank()) || GatewayConstants.BANK_HDFC.equalsIgnoreCase(paymentEntity.getBank())) {
				// Checking if constraints are met
				if (GatewayConstants.BANK_SBI.equalsIgnoreCase(paymentEntity.getBank()) && GatewayConstants.VENDOR_AMAZON.equalsIgnoreCase(paymentEntity.getVendor())
						|| GatewayConstants.BANK_HDFC.equalsIgnoreCase(paymentEntity.getBank()) && GatewayConstants.VENDOR_FLIPKART.equalsIgnoreCase(paymentEntity.getVendor())) {
					// Checking if valid amount is requested
					if(GatewayConstants.BANK_HDFC.equalsIgnoreCase(paymentEntity.getBank())) {
						HdfcUserBal hdfcUserBal = restTemplate.getForObject(hdfcURL + id, HdfcUserBal.class);
						if(hdfcUserBal.getBalance() < Integer.valueOf(paymentEntity.getAmount())) {
							txn.setStatusCode(propLoader.getProperty(TRANSACTION_FAILURE));
							txn.setStatusMessage(propLoader.getProperty(HDFC_BALANCE_ERROR));
						} else {
							Integer currBal = hdfcUserBal.getBalance();
							Integer cost = Integer.valueOf(paymentEntity.getAmount());

							HttpHeaders headers = new HttpHeaders();
							headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
							HttpEntity<HdfcUserBal> httpEntity = new HttpEntity<HdfcUserBal>(new HdfcUserBal(id, currBal - cost));
							restTemplate.exchange(hdfcPostURL, HttpMethod.POST, httpEntity, HdfcUserBal.class);
							
							txn.setStatusCode(propLoader.getProperty(TRANSACTION_SUCCESS));
							txn.setStatusMessage(propLoader.getProperty(TRANSACTION_NO_ERROR));							
						}
					} else {
						// add code for SBI
					}
				} else {
					txn.setStatusCode(propLoader.getProperty(TRANSACTION_FAILURE));
					txn.setStatusMessage(propLoader.getProperty(TRANSACTION_VENDOR_ERROR));
				}
			} else {
				txn.setStatusCode(propLoader.getProperty(TRANSACTION_FAILURE));
				txn.setStatusMessage(propLoader.getProperty(TRANSACTION_BANK_ERROR));
			}
		}
		transactionService.performTransaction(txn);
		return txn.getId();
	}
}