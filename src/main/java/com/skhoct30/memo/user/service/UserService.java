package com.skhoct30.memo.user.service;

import org.springframework.stereotype.Service;

import com.skhoct30.memo.common.MD5HashingEncoder;
import com.skhoct30.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	// final : 해당 변수에 값이 저장된 후 수정 불가능. << 이런 것들은 사실 변수가 아니라 상수다.
	private final UserRepository userRepository;
	
	
	
	// userserivce 가 생성될 때 Spring 이 알아서 이 생성자를 호출해서 객체를 주입해준다.
	// 다른 생성자 없이 Autowired 를 위한 생성자만 있는 경구 Autowired 어노테이션 생략 가능.
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 사용자 추가 기능
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String hashingPassword = MD5HashingEncoder.encode(password);
		
		// 실행된 행의 성공여부!
		// 실행된다 하면 1행 실패는 없음
		int count = userRepository.insertUser(loginId, hashingPassword, name, email);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
