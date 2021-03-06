package com.nemanja97.Projectpost.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemanja97.Projectpost.dto.CommentDTO;
import com.nemanja97.Projectpost.dto.TagDTO;
import com.nemanja97.Projectpost.entity.Tag;
import com.nemanja97.Projectpost.service.PostServiceInterface;
import com.nemanja97.Projectpost.service.TagServiceInterface;

@RestController
@RequestMapping(value="api/tag")
public class TagController {
	
	@Autowired
	private TagServiceInterface tagService;
	
	@Autowired
	private PostServiceInterface postService;
	
	@GetMapping
	public ResponseEntity<List<TagDTO>> getTags(){
		List<Tag> tags = tagService.findAll();
		List<TagDTO> tagDTO = new ArrayList<TagDTO>();
		for(Tag t: tags) {
			tagDTO.add(new TagDTO(t));
		}
		return new ResponseEntity<List<TagDTO>>(tagDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/post/{id}")
	public ResponseEntity<List<TagDTO>> getTagById(@PathVariable("id") Integer id){
		List<Tag> tags = tagService.findByPosts_Id(id);
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		for(Tag t: tags) {
			tagsDTO.add(new TagDTO(t));
		}
		return new ResponseEntity<List<TagDTO>>(tagsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<TagDTO> saveTag(@RequestBody TagDTO tagDTO){
		Tag tag = new Tag();
		tag.setName(tagDTO.getName());
		
		tag = tagService.save(tag);
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDTO, @PathVariable("id") Integer id ){
		Tag tag = tagService.findOne(id);
		if(tag == null) {
			return new ResponseEntity<TagDTO>(HttpStatus.BAD_REQUEST);
		}
		
		tag.setName(tagDTO.getName());
		
		tag = tagService.save(tag);
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deleteTag(@PathVariable("id") Integer id){
		Tag tag = tagService.findOne(id);
		if(tag != null) {
			tagService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
