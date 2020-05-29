package com.ooha.services;

import javax.servlet.http.HttpServletResponse;

import com.ooha.mongo.entity.TokenEntity;
import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;

public interface ModuleServices {
	public void createUser(UserEntity userModel);

	public void createToken(TokenEntity tokenModel);

	public TokenEntity getTokenByUserOjectId(String userObjectId);

	public String loginUserServices(LoginModel loginModel, HttpServletResponse req);

	public TokenEntity getTokenById(String id);
	
	public void deleteToken(TokenEntity token);
}
