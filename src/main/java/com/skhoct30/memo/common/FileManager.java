package com.skhoct30.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	// final 은 최종 = 수정할 수 없는 값이다. 상수.
	// static 객체생성 없이 편리하게 쓰기 위해서 쓰는 부분이 있다.
	public static final String FILE_UPLOAD_PATH = "D:\\seo_kang_hyun\\springProject\\upload\\memo";
	
	// 파일 저장 기능
	// 저장된 파일을 클라이언트가 접근 할 수 있는 url 경로를 return
	public static String saveFile(long userId, MultipartFile file) {
		
		// 파일이 비필수 일때 null 일때 저장하는 과정이 진행되면 안된다.
		if(file == null) {
			return null;
		}
		
		
		
		
		// 사용자가 업로드한 파일을 쭉 저장할것임.
		// 업로드한 파일은 그대로 유지하면서 저장하는 것이 목표
		// 여러 사용자가 올리다보니 같은 이름이 무조건 있을것임.
		// 이걸 명확하게 구분해주는 것이
		
		
		// 파일이름 유지
		// 폴더(디렉토리) 만들어서 저장
		// 사용자 정보를 폴더 이름으로 저장해서 사용
		// 시간정보를 포함하자. ( 간결하게 다룰 수 있는 시간정보 )
		// UNIX TIME : 1970년 1월 1일 0 시 0분 0초 부터 흐른 시간을 milli second (1 / 1000  천이면 1초) 단위로 포현한 값.
		// ex) 2_899293231
		// 앞에 2는 로그인한 사용자의 프라이머리 키 로그인한 사용자의 정보.
		
		// 경로부터 만들기
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 폴더(디렉토리) 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		} 
		
		
		
		// 파일 저장하기.
		// 디렉토리에 대한 경로는 만들었으니. 뒤에 파일 이름만 붙히면 된다
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
		
		
		
		
		// 실제 파일 저장 위치와 url 경로를 매칭하는 규칙
		// 을 정해서 그 규칙대로 url 경로르 만들어서 return 한다.
		
		// ex )  D:\\seo_kang_hyun\\springProject\\upload/2_51234543/test.png
		// /images/2_51234543/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	
	
	// 파일 삭제 기능
	
	public static boolean removeFile(String filePath) { // /images/2_51234543/test.png
		
		if(filePath == null) {
			return false;
		}
		
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/image", "");
		
		Path path = Paths.get(fullFilePath);
		
		
		//  D:\\seo_kang_hyun\\springProject\\upload/2_51234543/test.png
		Path dirPath = path.getParent();
		try {
			Files.delete(path);
			Files.delete(dirPath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
