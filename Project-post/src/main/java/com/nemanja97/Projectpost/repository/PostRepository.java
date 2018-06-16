package com.nemanja97.Projectpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.Post;
import com.nemanja97.Projectpost.entity.Tag;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByComments_Id(Integer id);
	List<Post> findByTags_Id(Integer id);
	
}
