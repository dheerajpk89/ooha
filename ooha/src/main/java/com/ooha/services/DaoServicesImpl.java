package com.ooha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooha.dao.TokenRepository;
import com.ooha.dao.UserRepository;
import com.ooha.mongo.entity.TokenEntity;
import com.ooha.mongo.entity.UserEntity;

@Service
public class DaoServicesImpl implements DaoServices {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void createUser(UserEntity userEntity) {
		userRepository.save(userEntity);
	}

	@Override
	public void createToken(TokenEntity tokenEntity) {
		tokenRepository.save(tokenEntity);
	}

	@Override
	public UserEntity getUserDetails(String userId) {
		return userRepository.getByUserID(userId);
	}

	@Override
	public TokenEntity getTokenByUserOjectId(String userObjectId) {
		return tokenRepository.getByUserOjectId(userObjectId);
	}

	@Override
	public TokenEntity getTokenById(String id) {
		return tokenRepository.getById(id);
	}

	@Override
	public void deleteToken(TokenEntity token) {
		tokenRepository.delete(token);
	}
	
	public UserEntity getUserDetailsById(String id) {
		return userRepository.getById(id);
	}

}
