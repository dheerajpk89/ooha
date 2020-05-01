package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.model.*;

public interface RegisterRepository extends MongoRepository<UserDetails, String> {

}
