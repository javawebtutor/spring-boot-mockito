package com.jwt.spring.mockito.api.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwt.spring.mockito.api.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{
	
	List<User> findByAddress(String address);

}
