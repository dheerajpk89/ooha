package com.ooha.services;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ooha.mongo.entity.TokenEntity;
import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;
import com.ooha.utils.CommonUtill;
import com.ooha.utils.PasswordManager;
import com.ooha.utils.URIConstants;

@Service
public class ModuleServiceImpl implements ModuleServices {

	@Autowired
	private DaoServices daoServices;

	@Autowired
	private PasswordManager passwordManager;

	@Override
	public void createUser(UserEntity userEntity) {
		Date currentDate = CommonUtill.getCurrentUTCTime();
		userEntity.setId(CommonUtill.getObjectId());
		userEntity.setCreatedDate(currentDate.toString());
		userEntity.setUpdateDate(currentDate.toString());
		userEntity.setIsUserActive(true);
		userEntity.setPassword(passwordManager.hash(userEntity.getPassword()));
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
	public String loginUserServices(LoginModel loginModel, HttpServletResponse response, Model model) {
		String returnVal = URIConstants.APP_PATH_LOGIN;
		UserEntity user = daoServices.getUserDetails(loginModel.getUserId());
		if (user.getIsUserActive()
				&& passwordManager.checkPassword(loginModel.getPassword(), user.getPassword())) {
			returnVal = this.validateToken(user, response, model);
		}
		return returnVal;
	}

	private String validateToken(UserEntity user, HttpServletResponse response, Model model) {
		TokenEntity token = daoServices.getTokenByUserOjectId(user.getId());
		if (CommonUtill.isEmpty(token)) {
			token = TokenEntity.builder().roleType(user.getUserType()).userOjectId(user.getId()).userID(user.getUserID()).build();
			this.createToken(token);
			token = daoServices.getTokenByUserOjectId(user.getId());
		}
		Cookie cookie = new Cookie(URIConstants.APP_TOKEN, token.getId());
		//add cookie to response
		response.addCookie(cookie);
		model.addAttribute(URIConstants.USER_ATTRIBUTE, daoServices.getUserDetailsById(token.getUserOjectId()));
		return URIConstants.APP_PATH_DASHBOARD;

	}

	@Override
	public TokenEntity getTokenById(String id) {
		return daoServices.getTokenById(id);
	}

	@Override
	public void deleteToken(TokenEntity token) {
		daoServices.deleteToken(token);
	}

	@Override
	public UserEntity getUserDetailsById(String id) {
		return daoServices.getUserDetailsById(id);
	}

	@Override
	public UserEntity getUserByCookie(HttpServletRequest request) {
		UserEntity userEntity = null;
		TokenEntity tokenEntity = daoServices.getTokenById(this.getTokenFromCooke(request.getCookies()));
		if (!CommonUtill.isEmpty(tokenEntity)) {
			userEntity = daoServices.getUserDetailsById(tokenEntity.getUserOjectId());
		}
		return userEntity;

	}

	private String getTokenFromCooke(Cookie[] cookies) {
		String appToken = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (URIConstants.APP_TOKEN.equals(cookies[i].getName())) {
					appToken = cookies[i].getValue();
				}
			}
		}
		return appToken;
	}
}
