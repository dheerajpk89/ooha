package com.ooha.services;

import com.ooha.mongo.entity.TokenEntity;
import com.ooha.mongo.entity.UserEntity;

public interface DaoServices {

	public void createUser(UserEntity userModel);

	public void createToken(TokenEntity tokenModel);

	public UserEntity getUserDetails(String userId);

	public TokenEntity getTokenByUserOjectId(String userObjectId);
	
	public TokenEntity getTokenById(String id);

}
