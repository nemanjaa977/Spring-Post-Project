package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanja97.Projectpost.entity.Comment;
import com.nemanja97.Projectpost.repository.CommentRepository;

@Service
public class CommentService implements CommentServiceInterface {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment findOne(int commentId) {
		return commentRepository.getOne(commentId);
	}
	
	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	@Override 
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	@Override
	public void remove(int id) {
		commentRepository.deleteById(id);
	}
	
	@Override
	public List<Comment> findAllOrderByDate(Integer id) {
		return commentRepository.findAllByPost_IdOrderByDate(id);
	}

	@Override
	public List<Comment> findByPost(Integer id){
		return commentRepository.findByPost_Id(id);
	}

	@Override
	public List<Comment> findAllOrderByLike(Integer id) {
		return commentRepository.findAllByPost_IdOrderByLike(id);
	}

	@Override
	public List<Comment> findAllOrderByDislike(Integer id) {
		return commentRepository.findAllByPost_IdOrderByDislike(id);
	}
	
	
}
