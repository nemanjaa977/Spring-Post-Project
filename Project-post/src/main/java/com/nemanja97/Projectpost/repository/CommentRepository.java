package com.nemanja97.Projectpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByPost_Id(Integer id);
	List<Comment> findAllByPost_IdOrderByDate(Integer id);
	List<Comment> findAllByPost_IdOrderByLike(Integer id);
	List<Comment> findAllByPost_IdOrderByDislike(Integer id);
}
