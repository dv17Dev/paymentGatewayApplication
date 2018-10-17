package com.dev.HdfcVer.userBal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for the {@link HdfcUserBal} class.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@RestController
public class HdfcUserBalController {
	
	@Autowired
	private HdfcUserBalService userBalSer;
	
	@GetMapping("/hdfc/{userId}")
	public Optional<HdfcUserBal> getUserBal(@PathVariable String userId) {
		return userBalSer.getUserBal(userId);
	}
	
	@PostMapping("/hdfc")
	public void addUserBal(@RequestBody HdfcUserBal userBal) {
		userBalSer.addUserBal(userBal);
	}
	
	@PutMapping("/hdfc")
	public void updateUserBal(@RequestBody HdfcUserBal userBal) {
		userBalSer.updateUserBal(userBal);
	}
}
