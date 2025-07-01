package com.skhoct30.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	// 한명의 사용자의 메모 리스트만 조회
	
	// WHERE `userId` = #{} ORDER BY `id` desc
	public List<Post> findByUserIdOrderByIdDesc(long userId);
}
