package com.nemanja97.Projectpost.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemanja97.Projectpost.dto.PostDTO;
import com.nemanja97.Projectpost.entity.Post;
import com.nemanja97.Projectpost.service.PostServiceInterface;

@RestController
@RequestMapping(value="api/posts")
public class PostController {

	@Autowired
	private PostServiceInterface postService;
	
	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts(){
		List<Post> posts = postService.findAll();
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		for(Post p: posts) {
			postsDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable("id") Integer id){
		Post post = postService.findOne(id);
		if(post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setPhoto(postDTO.getPhoto());
		post.setDate(postDTO.getDate());
		post.setLike(postDTO.getLike());
		post.setDislike(postDTO.getDislike());
		
		post = postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.CREATED);
	}
}
