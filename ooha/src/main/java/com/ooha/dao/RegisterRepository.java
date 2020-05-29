package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.*;

public interface RegisterRepository extends MongoRepository<UserEntity, String> {

}
