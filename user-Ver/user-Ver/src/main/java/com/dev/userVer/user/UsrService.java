package com.dev.userVer.user;

/**
 * Class to expose {@link UsrRepository} interface.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsrService {

	@Autowired
	private UsrRepository usrRepository;	
	
	public List<Usr> getAllUsers() {
		List<Usr> userList = new ArrayList<Usr>();
		usrRepository.findAll().forEach(userList::add);
		return userList;
	}

	public Optional<Usr> getUser(String uId) {
		return usrRepository.findById(uId);
	}
	
	public void addUser(Usr user) {
		usrRepository.save(user);
	}

	public void updateUser(Usr user) {
		usrRepository.save(user);
	}

	public void deleteUser(String uId) {
		usrRepository.deleteById(uId);
	}
}
