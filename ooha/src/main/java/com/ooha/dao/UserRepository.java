package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

	public UserEntity getByUserID(String userID);

}
