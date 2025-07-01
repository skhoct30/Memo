package com.skhoct30.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skhoct30.memo.post.domain.Post;
import com.skhoct30.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	private PostService postService;
	
	PostController(PostService postService){
		this.postService = postService;
	}
	
	
	
	@GetMapping("/list-view")
	public String postList(
			HttpSession session
			, Model model) {
		
		
		// 로그인한 사용자 정보는 session에 있음.
		// 한 사용자(로그인한 사용자)의 메모 리스트를 원해.
		// 로그인한 사용자의 프라이머리키. 
		
		Long userId = (Long)session.getAttribute("userId");
		
		List<Post> postList = postService.getPostList(userId);
		
		
		//                   이 키를 통해서      얘를 쓸 수 있음
		model.addAttribute("memoList", postList);
		
		
		
		return "post/list";
	}
	
	
	// 입력화면을 위한 view 페이지 만들기
	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}
	
	
	@GetMapping("/detail-view")
	public String postDetail(
			@RequestParam long id
			, Model model) {
		
		Post post = postService.getPost(id);
		
		model.addAttribute("memo", post);
		
		return "post/detail";
	}
	
}
