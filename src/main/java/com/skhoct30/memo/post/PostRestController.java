package com.skhoct30.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostService postService;

	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	
	
	
	//
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam String title
			, @RequestParam String contents
			, @RequestParam(required=false) MultipartFile imageFile
			, HttpSession session) {
		
		// 설계 테이블 보면 작성자의 정보는 가져와야함 session(로그인한 사용자의 정보)
		
		// 키는 로그인할 때 저장된 키 프라이머리키 (userId) 로 저장된 값으 얻어오자.
		
		// 얘는 데이터가 오브젝트에 있어서 다운캐스팅으로 long 을 변경해줘야함.
		// 
		Long userId = (Long)session.getAttribute("userId");
		
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.addPost(userId, title, contents, imageFile)) {
			resultMap.put("result", "success");
			
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
 