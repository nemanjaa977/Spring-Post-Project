package com.nemanja97.Projectpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	List<Tag> findByPosts_Id(Integer id);
	
}
