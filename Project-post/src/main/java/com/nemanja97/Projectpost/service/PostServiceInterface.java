package com.nemanja97.Projectpost.service;

import java.util.List;

import com.nemanja97.Projectpost.entity.Post;

public interface PostServiceInterface {
	
	Post findOne(int productId);
	List<Post> findAll();
	Post save(Post post);
	void remove(int id);
//	Post findByComments(Integer id);
	
}
