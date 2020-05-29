package com.ooha.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;

@Controller
public class GetAppControllers {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginModel", LoginModel.builder().userId("userId").password("password").build());
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(Model model) {
		final UserEntity userModel = UserEntity.builder().userID("userID").password("password").userType("userType").firstName("firstName")
				.lastName("lastName").mobile("mobile").emailId("emailId").address("address").country("country")
				.build();

		model.addAttribute("userModel", userModel);
		return "/register";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "/dashboard";
	}

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables() {
		return "/tables";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "/";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accessDenied() {
		System.out.println("denied");
		return "/error/access-denied";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Model model) {
		LoginModel loginModel = LoginModel.builder().password("password").userId("userId").build();
		model.addAttribute("loginModel", loginModel);
		return "index";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userIndex() {
		return "dashbord";
	}


	@RequestMapping(value = "/new_inventory", method = RequestMethod.GET)
	public String newInventory() {
		return "new_inventory";
	}

	@RequestMapping(value = "/inventory_report", method = RequestMethod.GET)
	public String inventoryReport() {
		return "inventory_report";
	}
	
	@RequestMapping(value = "/others_inventory_report", method = RequestMethod.GET)
	public String othersInventoryReport() {
		return "others_inventory_report";
	}
	
	@RequestMapping(value = "/asset_add", method = RequestMethod.GET)
	public String assetAdd() {
		return "asset_add";
	}
	
	@RequestMapping(value = "/asset_calendar", method = RequestMethod.GET)
	public String assetCalendar() {
		return "asset_calendar";
	}
	
	@RequestMapping(value = "/assets_list", method = RequestMethod.GET)
	public String assetsList() {
		return "assets_list";
	}
	
	@RequestMapping(value = "/assets_map", method = RequestMethod.GET)
	public String assetsMap() {
		return "assets_map";
	}
	

}
