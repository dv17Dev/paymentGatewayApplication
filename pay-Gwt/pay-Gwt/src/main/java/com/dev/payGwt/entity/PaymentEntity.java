package com.dev.payGwt.entity;

/**
 * Class to contain the basic information provided by the user.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

public class PaymentEntity {
	
	private String userId;
	private String vendor;
	private String bank;
	private String payMethod;
	private String amount;
	
	public PaymentEntity(String userId, String vendor, String bank, String payMethod, String amount) {
		super();
		this.userId = userId;
		this.vendor = vendor;
		this.bank = bank;
		this.payMethod = payMethod;
		this.amount = amount;
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
}
