package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanja97.Projectpost.entity.Post;
import com.nemanja97.Projectpost.repository.PostRepository;

@Service
public class PostService implements PostServiceInterface{
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post findOne(int postId) {
		return postRepository.getOne(postId);
	}
	
	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	@Override 
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public void remove(int id) {
		postRepository.deleteById(id);
	}

//	@Override
//	public Post findByComments(Integer id) {
//		return postRepository.findByComments_Id(id);
//	}
	

}
