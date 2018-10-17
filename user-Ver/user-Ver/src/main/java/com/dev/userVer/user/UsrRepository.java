package com.dev.userVer.user;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface to enable JPA.
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

public interface UsrRepository extends CrudRepository<Usr, String> {

}
