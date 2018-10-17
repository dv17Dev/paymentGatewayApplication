package com.dev.payGwt.transaction;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class to maintain user transactions.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@Entity
public class Transactions {
	@Id
	private String id;
	
	private String vendor;
	private String bank;
	private String payMethod;
	private String amount;
	private String timeStamp;
	private String statusCode;
	private String statusMessage;
	private String userName;

	private String userId;
	
	public Transactions() {
		
	}
	
	public Transactions(String id, String vendor, String bank, String payMethod, String amount, String timeStamp) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.bank = bank;
		this.payMethod = payMethod;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
