package com.skhoct30.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {
	
	// 문자열을 md5로 해싱한 결과 만들기
	// 꼭 비밀번호가 아니여도 되고 
	// 어떤 문자열이든 해싱을 사용해서 만들 수 있다는걸 지금 예제
	
	public static String encode(String message) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("mb5");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			// 해싱된 결과가 바이트 라는 타입의 배열에 들어가있다.
			// 이 바이트라는 형태는 정수, 이 정수형태를 16진수 숫자로 변경해서 문자열로 구성을 시킬것이다.
			byte[] digest = messageDigest.digest();
			
			
			// 하나씩 바이트 배열에 접근해서 이어붙히는 과정을 시작할것이다.
			String result = "";
			for(int i = 0; i < digest.length; i++) {
				// byte 연산
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
			return result;
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
		}
		
		
	}

}
