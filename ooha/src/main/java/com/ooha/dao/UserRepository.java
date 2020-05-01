package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {

}
