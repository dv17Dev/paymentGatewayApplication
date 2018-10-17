package com.dev.payGwt.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to expose {@link TransactionReposity}.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;

	public List<Transactions> getAllTransactions() {
		List<Transactions> txnList = new ArrayList<Transactions>();
		transactionRepo.findAll().forEach(txnList::add);
		return txnList;
	}
	
	public List<Transactions> getTransactionsByUserId(String id) {
		List<Transactions> txnList = new ArrayList<Transactions>();
		transactionRepo.findByUserId(id).forEach(txnList::add);
		return txnList;
	}
	
	public Optional<Transactions> getTransactionByTxnId(String id) {
		return transactionRepo.findById(id);
	}
	
	public void performTransaction(Transactions txn) {
		transactionRepo.save(txn);
	}
	
	public void updateTransaction(Transactions txn) {
		transactionRepo.save(txn);
	}
	
	public void deleteTransaction(String id) {
		transactionRepo.deleteById(id);;
	}	
}
