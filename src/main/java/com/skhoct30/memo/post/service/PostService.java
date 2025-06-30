package com.skhoct30.memo.post.service;

import org.springframework.stereotype.Service;

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
			, String contents) {
		
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.build();
		
		try {
			postRepository.save(post);			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
}
