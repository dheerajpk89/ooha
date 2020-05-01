package com.ooha.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class UserImpl implements UserRepository {

	@Autowired
	private  MongoTemplate mongoTemplate;


}
