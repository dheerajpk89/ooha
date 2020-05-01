package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.model.HospitalModel;

public interface HospitalRepository extends MongoRepository<HospitalModel, String> {

}
