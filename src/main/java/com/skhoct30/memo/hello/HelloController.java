package com.skhoct30.memo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@GetMapping("/hello")
	@ResponseBody
	public String helloWorld() {
		return "Hello World!!";
	}
	
	// html 테스트
	@GetMapping("/hello/thymeleaf")
	public String helloThymeleaf() {
		return "hello/hello";
	}
	
	
}
