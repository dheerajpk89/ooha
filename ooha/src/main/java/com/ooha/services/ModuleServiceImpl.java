package com.ooha.services;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooha.mongo.entity.TokenEntity;
import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;
import com.ooha.utils.CommonUtill;

@Service
public class ModuleServiceImpl implements ModuleServices {

	@Autowired
	private DaoServices daoServices;

	@Override
	public void createUser(UserEntity userEntity) {
		Date currentDate = CommonUtill.getCurrentUTCTime();
		userEntity.setId(CommonUtill.getObjectId());
		userEntity.setCreatedDate(currentDate.toString());
		userEntity.setUpdateDate(currentDate.toString());
		userEntity.setIsUserActive(true);
		daoServices.createUser(userEntity);

	}

	@Override
	public void createToken(TokenEntity tokenEntity) {
		Date currentDate = CommonUtill.getCurrentUTCTime();
		tokenEntity.setId(CommonUtill.getObjectId());
		tokenEntity.setCreatedDate(currentDate.toString());
		daoServices.createToken(tokenEntity);

	}

	@Override
	public TokenEntity getTokenByUserOjectId(String userObjectId) {
		return daoServices.getTokenByUserOjectId(userObjectId);
	}

	@Override
	public String loginUserServices(LoginModel loginModel, HttpServletResponse response) {

		String returnVal = "login";

		UserEntity user = daoServices.getUserDetails(loginModel.getUserId());
		if (user.getIsUserActive()
				&& user.getPassword().equals(loginModel.getPassword())) {
			returnVal = this.validateToken(user, response);
		}

		return returnVal;
	}

	private String validateToken(UserEntity user, HttpServletResponse response) {
		TokenEntity token = daoServices.getTokenByUserOjectId(user.getId());
		if (CommonUtill.isEmpty(token)) {
			token = TokenEntity.builder().roleType(user.getUserType()).userOjectId(user.getId()).userID(user.getUserID()).build();
			this.createToken(token);
			token = daoServices.getTokenByUserOjectId(user.getId());
		}

		Cookie cookie = new Cookie("app-token", token.getId());
		//add cookie to response
		response.addCookie(cookie);
		return "dashboard";

	}

	@Override
	public TokenEntity getTokenById(String id) {
		return daoServices.getTokenById(id);
	}
}
