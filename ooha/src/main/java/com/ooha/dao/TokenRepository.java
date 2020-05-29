package com.ooha.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ooha.mongo.entity.TokenEntity;

public interface TokenRepository extends MongoRepository<TokenEntity, String> {

	public TokenEntity getByUserOjectId(String userOjectId);

	public TokenEntity getById(String id);

}
