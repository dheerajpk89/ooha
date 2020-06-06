package com.ooha.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import com.ooha.mongo.entity.TokenEntity;
import com.ooha.services.ModuleServices;
import com.ooha.utils.AppCoreConstants;
import com.ooha.utils.CommonUtill;
import com.ooha.utils.URIConstants;

@Controller
public class AccessValidateFilter extends GenericFilterBean {

	@Autowired
	private ModuleServices services;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Boolean isvalied = true;
		/**Checking the url patran is login or other */
		if (request.getServletPath().equals(URIConstants.APP_PATH_LOGIN)) {
			/** if method type is POST the user is trying to login to create token*/
			if (request.getMethod().equals("POST")) {
				System.out.println(request.getMethod()
						+ "Executing the filter call...!"
						+ request.getServletPath());
			}
		} else if (checkUrlPath(request.getServletPath())) {
			/**if not login then we need to validate the token */
			isvalied = this.validateAppToken(request, response);
		} else if (request.getServletPath().equals(URIConstants.APP_PATH_LOGOUT)) {
			this.removeToken(request, response);
			isvalied = false;
		}
		if (!isvalied) {
			response.sendRedirect(this.redirect(URIConstants.APP_PATH_LOGIN, request));
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	private String redirect(String finalPath, HttpServletRequest request) {
		return "http://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ finalPath;
	}

	public Boolean validateAppToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Boolean isValied = true;
		String appToken = this.getTokenFromCooke(request.getCookies());
		if (!CommonUtill.isEmpty(appToken)) {
			TokenEntity token = services.getTokenById(appToken);
			if (CommonUtill.isEmpty(token)) {
				isValied = false;
			}
		}
		return isValied;
	}

	private void removeToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String appToken = this.getTokenFromCooke(request.getCookies());
		if (!CommonUtill.isEmpty(appToken)) {
			TokenEntity token = services.getTokenById(appToken);
			if (!CommonUtill.isEmpty(token)) {
				services.deleteToken(token);
			}
		}
	}

	private String getTokenFromCooke(Cookie[] cookies) {
		String appToken = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("app-token".equals(cookies[i].getName())) {
					appToken = cookies[i].getValue();
				}
			}
		}
		return appToken;
	}

	private Boolean checkUrlPath(String url) {
		return !url.contains(URIConstants.APP_PATH_LOGOUT)
				&& !url.contains(URIConstants.APP_PATH_REGISTER)
				&& !url.contains(".css")
				&& !url.contains(".js")
				&& !url.contains(".jpg")
				&& !url.contains(".png")
				&& !url.contains(".woff")
				&& !url.contains(".woff2");
	}
}