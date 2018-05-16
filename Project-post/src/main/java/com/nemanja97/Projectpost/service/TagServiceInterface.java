package com.nemanja97.Projectpost.service;

import java.util.List;

import com.nemanja97.Projectpost.entity.Tag;

public interface TagServiceInterface {
	
	Tag findOne(int id);
	List<Tag> findAll();
	Tag save(Tag tag);
	void remove(int id);
	
}
