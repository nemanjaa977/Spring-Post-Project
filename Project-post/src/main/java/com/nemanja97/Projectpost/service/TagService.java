package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanja97.Projectpost.entity.Tag;
import com.nemanja97.Projectpost.repository.TagRepository;

@Service
public class TagService implements TagServiceInterface {
	
	@Autowired
	TagRepository tagRepository;
	
	@Override
	public Tag findOne(int tagId) {
		return tagRepository.getOne(tagId);
	}
	
	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}
	
	@Override 
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}
	
	@Override
	public void remove(int id) {
		tagRepository.deleteById(id);
	}
	
	@Override
	public List<Tag> findByPosts_Id(Integer id){
		return tagRepository.findByPosts_Id(id);
	}
	
}
