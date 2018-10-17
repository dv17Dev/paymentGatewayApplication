package com.dev.HdfcVer.userBal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to expose the {@linkplain HdfcUserBalRepository} interface.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@Service
public class HdfcUserBalService {
	
	@Autowired
	private HdfcUserBalRepository userBalRepo;
	
	public Optional<HdfcUserBal> getUserBal(String id) {
		return userBalRepo.findById(id);
	}
	
	public void addUserBal(HdfcUserBal userBal) {
		userBalRepo.save(userBal);
	}
	
	public void updateUserBal(HdfcUserBal userBal) {
		userBalRepo.save(userBal);
	}
}
