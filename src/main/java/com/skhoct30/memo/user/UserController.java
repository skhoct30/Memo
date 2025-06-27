package com.skhoct30.memo.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


// view 를 위한 Controller
@RequestMapping("/user")
@Controller
public class UserController {
	

	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String loginInput() {
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// 세션에 사용자 정보를 반대로 하는 과정
		
//		HttpSession session = request.getSession();
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/login-view";
		
	}
	
	
}
