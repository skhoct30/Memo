package com.skhoct30.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.memo.common.FileManager;
import com.skhoct30.memo.post.domain.Post;
import com.skhoct30.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {

    private final PostRepository postRepository;

	// userId title contents
	// 받아오자 전달받자
	
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	
	public boolean addPost(
			long userId
			, String title
			, String contents
			, MultipartFile File) {
		
		// 파일추가하는 기능
		String imagePath = FileManager.saveFile(userId, File);
		
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			postRepository.save(post);			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	
	// 리스트를 불러오는 기능 ( 한 사용자의 메모 리스트 )
	// 특정 사용자가 작성한 메모만 조회하고 싶으면
	// userId 를 우리가 정보를 넣고 있으니까. 저걸로 조건을 걸자
	public List<Post> getPostList(long userId) {
		
		// repository 에서 가져와야하는데 jpa 기능으로 수행중이니까 거기에 맞춰서 작성
		
		List<Post> postList = postRepository.findByUserIdOrderByIdDesc(userId);
		
		
		return postList;
	}
	
	
	// 메뫃 한개의 내용 조회
	// 프라이머리 키로 메모하나를 얻어와
	// 프라이머리 키로 한행의 정보가 일치하는지 찾아오기
	
	public Post getPost(long id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		// null 처리를 해야함
		// 있는지 없는지에 대해서 ?
		
		if(optionalPost.isPresent()) {
			// true
			return optionalPost.get();
		} else {
			// false
			return null;
		}
		
	}
	
	
	// 수정하는 기능 boolean 성공 실패여부 메모 수정이 된건지 아닌지
	public boolean updatePost(
			long id
			, String title
			, String contents) {
		
		// 널인지 아닌지 그리고 얻어오기위해서 .
		// Optional 로 전달받은 아이디를 확인해야함. 게시물 수정하니까 하나 클릭했을 때 그 게시물의 id ㄱ값을 얻어오는 그런그림
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			
			Post post = optionalPost.get();
			
			post = post.toBuilder()
			.title(title)
			.contents(contents)
			.build();
			
			
			try {
				postRepository.save(post);
				
			} catch(PersistenceException e) {
				return false;
			}

		} else {
			return false;
		}
		return true;
		
		
	}
	
	
	
	
	//삭제기능
	
	public boolean deletePost(long id) {
		
		// 한행의 정보를 가져오면 좋음
		Optional<Post> optionalPost = postRepository.findById(id);
		
		
		if(optionalPost.isPresent()) {
			
			Post post = optionalPost.get();
			
			FileManager.removeFile(post.getImagePath()); // 포스트 안에 이미지 패스의 경로 (값을 전달하자 파일매니저에)
						
			
			// 삭제대상 가져오기
			postRepository.delete(post);
			
			
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
}
