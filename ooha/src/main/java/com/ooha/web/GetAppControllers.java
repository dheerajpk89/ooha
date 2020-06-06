package com.ooha.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooha.mongo.entity.UserEntity;
import com.ooha.mongo.model.LoginModel;
import com.ooha.services.ModuleServices;
import com.ooha.utils.URIConstants;

@Controller
public class GetAppControllers {

	@Autowired
	private ModuleServices services;

	@RequestMapping(value = URIConstants.APP_PATH_LOGIN, method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginModel", LoginModel.builder().userId("userId").password("password").build());
		return "login";
	}

	@RequestMapping(value = URIConstants.APP_PATH_REGISTER, method = RequestMethod.GET)
	public String registerGet(Model model) {
		final UserEntity userModel = UserEntity.builder().userID("userID").password("password").userType("userType").firstName("firstName")
				.lastName("lastName").mobile("mobile").emailId("emailId").address("address").country("country").build();

		model.addAttribute("userModel", userModel);
		return URIConstants.APP_PATH_REGISTER;
	}

	@RequestMapping(value = URIConstants.APP_PATH_DASHBOARD, method = RequestMethod.GET)
	public String dashboard(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_DASHBOARD;
	}

	@RequestMapping(value = URIConstants.APP_PATH_TABLES, method = RequestMethod.GET)
	public String tables(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_TABLES;
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accessDenied(Model model, HttpServletRequest request) {
		System.out.println("denied");
		return "/error/access-denied";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Model model, HttpServletRequest request) {
		LoginModel loginModel = LoginModel.builder().password("password").userId("userId").build();
		model.addAttribute("loginModel", loginModel);
		return "index";
	}

	@RequestMapping(value = URIConstants.APP_PATH_USER, method = RequestMethod.GET)
	public String userIndex(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_DASHBOARD;
	}

	@RequestMapping(value = URIConstants.APP_PATH_NEW_INVENTORY, method = RequestMethod.GET)
	public String newInventory(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_NEW_INVENTORY;
	}

	@RequestMapping(value = URIConstants.APP_PATH_INVENTORY_REPORT, method = RequestMethod.GET)
	public String inventoryReport(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_INVENTORY_REPORT;
	}

	@RequestMapping(value = URIConstants.APP_PATH_OTHERS_INVENTORY_REPORT, method = RequestMethod.GET)
	public String othersInventoryReport(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_OTHERS_INVENTORY_REPORT;
	}

	@RequestMapping(value = URIConstants.APP_PATH_OTHERS_ASSET_ADD, method = RequestMethod.GET)
	public String assetAdd(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_OTHERS_ASSET_ADD;
	}

	@RequestMapping(value = URIConstants.APP_PATH_ASSET_CALENDAR, method = RequestMethod.GET)
	public String assetCalendar(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_ASSET_CALENDAR;
	}

	@RequestMapping(value = URIConstants.APP_PATH_ASSETS_LIST, method = RequestMethod.GET)
	public String assetsList(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_ASSETS_LIST;
	}

	@RequestMapping(value = URIConstants.APP_PATH_ASSETS_MAP, method = RequestMethod.GET)
	public String assetsMap(Model model, HttpServletRequest request) {
		model.addAttribute(URIConstants.USER_ATTRIBUTE, services.getUserByCookie(request));
		return URIConstants.APP_PATH_ASSETS_MAP;
	}

}
