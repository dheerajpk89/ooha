package com.ooha.web;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;
import com.ooha.services.ModuleServices;
import com.ooha.utils.URIConstants;

@Controller
public class PostAppController {

	@Autowired
	private ModuleServices services;

	@RequestMapping(value = URIConstants.APP_PATH_LOGIN, method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "loginModel") final LoginModel loginModel, BindingResult bindingResult, Model model,
			HttpServletResponse req) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("loginModel", loginModel);
			return URIConstants.APP_PATH_LOGIN;
		}
		return services.loginUserServices(loginModel, req,model);
	}

	@RequestMapping(value = URIConstants.APP_PATH_REGISTER, method = RequestMethod.POST)
	public String registerGet(@Valid @ModelAttribute(value = "userModel") final UserEntity userModel, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userModel", userModel);
			return URIConstants.APP_PATH_REGISTER;
		}
		services.createUser(userModel);
		return URIConstants.APP_PATH_REGISTER;
	}
}
