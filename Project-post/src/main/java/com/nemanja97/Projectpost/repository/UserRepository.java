package com.nemanja97.Projectpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemanja97.Projectpost.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
