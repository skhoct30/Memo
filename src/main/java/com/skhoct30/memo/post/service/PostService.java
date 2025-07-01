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
	
	
	
}
