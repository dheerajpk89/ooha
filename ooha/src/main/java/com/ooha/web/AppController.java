package com.ooha.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooha.mongo.model.HospitalModel;
import com.ooha.mongo.model.LoginModel;
import com.ooha.mongo.model.UserModel;
import com.ooha.services.EledgerServices;

@Controller
public class AppController {

	@Autowired
	private EledgerServices eledgerServices;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "loginModel") final LoginModel loginModel) {
		System.out.println("here"
				+ loginModel.getUserId());
		/*	UserMongoDetailes userDetailes=eledgerServices.getUserDetail(loginModel);
			if(userDetailes!=null) {
				
				return "dashbord";
			}*/
		return "redirect:/";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute(value = "registerModel") final UserModel registerModel, Model model) {
		return "redirect:/";
	}

	@RequestMapping(value = "/hospital", method = RequestMethod.POST)
	public String createHospital(@Valid @ModelAttribute(value = "hospitalModel") final HospitalModel hospitalModel,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hospitalModel", hospitalModel);
			return "new_hospital";
		}
		eledgerServices.createHospital(hospitalModel);
		return "redirect:/hospital";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerGet(@Valid @ModelAttribute(value = "userModel") final UserModel userModel,
			BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userModel", userModel);
			return "register";
		}
		eledgerServices.createUser(userModel);
		return "/register";
	}
}
