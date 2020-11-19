package com.ssafy.happyhouse;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 페이지 이동 Controller
 */

@Controller
public class HomeController {
	
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String modify(Locale locale, Model model) {
		return "/happyhouse/modify";
	}
	
	@RequestMapping(value = "interestPage", method = RequestMethod.GET)
	public String interest(Locale locale, Model model) {
		return "/happyhouse/star_view";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) {
		return "/happyhouse/write";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "/home/index";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		return "/error";
	}
	
	@RequestMapping(value = "priceview", method = RequestMethod.GET)
	public String priceView(Locale locale, Model model) {
		return "/happyhouse/price_view";
	}
	
	@RequestMapping(value = "storeStar", method = RequestMethod.GET)
	public String storeStar(Locale locale, Model model) {
		return "/happyhouse/store_view";
	}
	
	@RequestMapping(value = "sitemap", method = RequestMethod.GET)
	public String siteMap(Locale locale, Model model) {
		return "/happyhouse/sitemap";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "/user/login";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
		return "/user/sign_up";
	}
	
	
	@RequestMapping(value = "management", method = RequestMethod.GET)
	public String management(Locale locale, Model model) {
		return "/user/management";
	}
	
	
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public String userlist(Locale locale, Model model) {
		return "/user/userlist";
	}
	
	
}
