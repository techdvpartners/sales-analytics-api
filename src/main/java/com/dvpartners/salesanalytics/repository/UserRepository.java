package com.dvpartners.salesanalytics.repository;

import org.springframework.data.repository.CrudRepository;

import com.dvpartners.salesanalytics.model.User;

public interface UserRepository extends CrudRepository<User, String>{

}
