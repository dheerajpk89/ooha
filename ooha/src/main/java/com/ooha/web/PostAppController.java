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

@Controller
public class PostAppController {

	@Autowired
	private ModuleServices services;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "loginModel") final LoginModel loginModel, BindingResult bindingResult, Model model,HttpServletResponse req) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("loginModel", loginModel);
			return "login";
		}
		return services.loginUserServices(loginModel,req);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute(value = "registerModel") final UserEntity registerModel, Model model) {
		return "redirect:/";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerGet(@Valid @ModelAttribute(value = "userModel") final UserEntity userModel, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userModel", userModel);
			return "register";
		}
		services.createUser(userModel);
		return "/register";
	}
}
