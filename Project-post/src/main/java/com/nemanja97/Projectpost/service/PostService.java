package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	@Override
	public List<Post> findByTags_Id(Integer id){
		return postRepository.findByTags_Id(id);
	}

	@Override
	public List<Post> findAllOrderByDate() {
		return postRepository.findAllByOrderByDate();
	}

	@Override
	public List<Post> findAllOrderByLike() {
		return postRepository.findAllByOrderByLike();
	}

	@Override
	public List<Post> findAllOrderByDislike() {
		return postRepository.findAllByOrderByDislike();
	}

}
