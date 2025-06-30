package com.skhoct30.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	
	@GetMapping("/list-view")
	public String postList() {
		
		
		return "post/list";
	}
	
	
	// 입력화면을 위한 view 페이지 만들기
	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}
	
}
