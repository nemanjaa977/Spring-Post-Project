package com.nemanja97.Projectpost.service;

import java.util.List;

import com.nemanja97.Projectpost.entity.Comment;


public interface CommentServiceInterface {
	
	Comment findOne(int id);
	List<Comment> findAll();
	Comment save(Comment comment);
	void remove(int id);
	
}
