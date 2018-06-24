package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.nemanja97.Projectpost.entity.Post;

public interface PostServiceInterface {
	
	Post findOne(int productId);
	List<Post> findAll();
	Post save(Post post);
	void remove(int id);
	List<Post> findByTags_Id(Integer id);
	
	List<Post> findAllOrderByDate();
	List<Post> findAllOrderByLike();
	List<Post> findAllOrderByDislike();
	
	List<Post> findAllBySearch(String text);
	
}
