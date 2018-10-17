package com.dev.HdfcVer.userBal;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class for maintaining user bank balance.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@Entity
public class HdfcUserBal {
	@Id
	private String userId;
	private Integer balance;
	
	public HdfcUserBal() {
		
	}
		
	public HdfcUserBal(String id, Integer balance) {
		super();
		this.userId = id;
		this.balance = balance;
	}

	public String getId() {
		return userId;
	}
	public void setId(String id) {
		this.userId = id;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
}
