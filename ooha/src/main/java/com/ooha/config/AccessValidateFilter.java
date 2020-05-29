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
import com.ooha.utils.CommonUtill;

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
		if (request.getServletPath().equals("/login")) {
			/** if method type is POST the user is trying to login to create token*/
			if (request.getMethod().equals("POST")) {
				System.out.println(request.getMethod()
						+ "Executing the filter call...!"
						+ request.getServletPath());
			}
		} else if (checkUrlPath(request.getServletPath())) {
			/**if not login then we need to validate the token */
			isvalied = this.validateAppToken(request, response);
		}
		if (!isvalied) {
			response.sendRedirect("http://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ "/login");
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	public Boolean validateAppToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Boolean isValied = true;
		Cookie[] cookies = request.getCookies();
		String appToken = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("app-token".equals(cookies[i].getName())) {
					appToken = cookies[i].getValue();
				}
			}
			if (!CommonUtill.isEmpty(appToken)) {
				TokenEntity token = services.getTokenById(appToken);
				if (CommonUtill.isEmpty(token)) {
					isValied = false;
				}
			}
		}
		return isValied;
	}

	private Boolean checkUrlPath(String url) {
		return !url.contains(".css")
				&& !url.contains(".js")
				&& !url.contains(".jpg")
				&& !url.contains(".png")
				&& !url.contains(".woff")
				&& !url.contains(".woff2");
	}
}