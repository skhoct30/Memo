package com.skhoct30.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.memo.user.service.UserService;

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
	

}
