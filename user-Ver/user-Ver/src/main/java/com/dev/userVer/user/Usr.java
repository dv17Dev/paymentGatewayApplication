package com.dev.userVer.user;

/**
 * Class to maintain user data.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usr {
	@Id
	private String userId;
	private String name;
	
	public Usr() {
		
	}
	
	public Usr(String id, String name) {
		super();
		this.userId = id;
		this.name = name;
	}
	public String getId() {
		return userId;
	}
	public void setId(String id) {
		this.userId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
