package com.nemanja97.Projectpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
