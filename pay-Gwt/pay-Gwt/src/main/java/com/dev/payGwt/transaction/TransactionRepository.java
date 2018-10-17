package com.dev.payGwt.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface to enable JPA.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

public interface TransactionRepository extends CrudRepository<Transactions, String> {
	public List<Transactions> findByUserId(String id);
}
