package com.skhoct30.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.memo.user.domain.User;
import com.skhoct30.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// API 구성을 위한 Controller
// responseBody 가 전부 들어간다.



@RequestMapping("/user")
@RestController // 여기 들어가는 놈들은 전부 다 @Controller + @ResponseBody 가 들어간다
public class UserRestController {
	
	
	private UserService userService;

	//@Autowired 가 들어가야하지만 이제는 생략가능!!
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	// 파라미터? 고민? 이거는 내가 설계 문서에서 이미 다 작성해놨잖아
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam String loginId
			, @RequestParam String password
			, @RequestParam String name
			, @RequestParam String email) {
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		// if 문 쓰는 이유는 Service 에서 boolean 을 사용해서 true false 를 사용해서 그럼
		if(userService.addUser(loginId, password, name, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
	
	
	// 로그인을 위한 API
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam String loginId
			, @RequestParam String password
			, HttpServletRequest request) {
		
		// 일단 일치하는 행이 있는지를 확인해야한다
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success");
			
			// 새션을 관리하는 객체
			// 요청한 대상 클라이언트의 새션
			HttpSession session = request.getSession();
			
			
			// 로그인이 되었을 때 전달할 새션
			// 사용자 정보를 필요한 만큼 저장해서 일부활용하기
			// 세션은 모든 요청에서 접근하고 사용할 수 있다.
			
			// 사용자의 정보를 저장하는 세션
			// 키 밸류
			// 사용자의 아이디 , 
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	

}
