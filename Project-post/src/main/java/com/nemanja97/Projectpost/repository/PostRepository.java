package com.nemanja97.Projectpost.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByComments_Id(Integer id);
	List<Post> findByTags_Id(Integer id);
	
	List<Post> findAllByOrderByDate();
	List<Post> findAllByOrderByLike();
	List<Post> findAllByOrderByDislike();
}
