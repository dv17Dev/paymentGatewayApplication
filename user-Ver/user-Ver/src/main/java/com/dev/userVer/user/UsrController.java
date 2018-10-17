package com.dev.userVer.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for the {@link Usr} class.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@RestController
public class UsrController {
	
	@Autowired
	private UsrService usrService;
	
	@GetMapping("/users")
	public List<Usr> getAllUsers() {
		return usrService.getAllUsers();
	}
	
	@GetMapping("/users/{uId}")
	public Optional<Usr> getUser(@PathVariable String uId) {
		return usrService.getUser(uId);
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody Usr user) {
		usrService.addUser(user);
	}
	
	@PutMapping("/users")
	public void updateUser(@RequestBody Usr user) {
		usrService.updateUser(user);
	}
	
	@DeleteMapping("/users/{uId}")
	public void deleteUser(@PathVariable String uId) {
		usrService.deleteUser(uId);
	}
}
