package com.ssafy.happyhouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 페이지 이동 Controller SPA
 */

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping("/sitemap")
	public String siteMap() {
		return "index.html";
	}

	@RequestMapping("/signup")
	public String singUp() {
		return "index.html";
	}

	@RequestMapping("/storeview")
	public String storeView() {
		return "index.html";
	}

	@RequestMapping("/starview")
	public String starView() {
		return "index.html";
	}
	
	@RequestMapping("/priceview")
	public String priceView() {
		return "index.html";
	}
	
	@RequestMapping("/notice")
	public String notice() {
		return "index.html";
	}
	
	@RequestMapping("/management")
	public String management() {
		return "index.html";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "index.html";
	}
	
	@RequestMapping("/guestbook")
	public String guestBook() {
		return "index.html";
	}
	@RequestMapping("/userlist")
	public String userList() {
		return "index.html";
	}
	

}
