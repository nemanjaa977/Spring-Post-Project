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
import com.nemanja97.Projectpost.dto.PostDTO;
import com.nemanja97.Projectpost.entity.Comment;
import com.nemanja97.Projectpost.entity.Post;
import com.nemanja97.Projectpost.entity.Tag;
import com.nemanja97.Projectpost.service.PostServiceInterface;
import com.nemanja97.Projectpost.service.TagServiceInterface;
import com.nemanja97.Projectpost.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/posts")
public class PostController {

	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private TagServiceInterface tagService;
	
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
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setPhoto(postDTO.getPhoto());
		post.setDate(postDTO.getDate());
		post.setLike(postDTO.getLike());
		post.setDislike(postDTO.getDislike());
		post.setLongitude(postDTO.getLongitude());
		post.setLatitude(postDTO.getLatitude());
		post.setUser(userService.findOne(postDTO.getUserDTO().getId()));
		post = postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Integer id ){
		Post post = postService.findOne(id);
		if(post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}
		
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setPhoto(postDTO.getPhoto());
		post.setDate(postDTO.getDate());
		post.setLike(postDTO.getLike());
		post.setDislike(postDTO.getDislike());
		
		post = postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}
	
	@PutMapping(value="/setTags/{postId}/{tagId}",consumes = "application/json")
	public ResponseEntity<PostDTO> setTagsInPost(@PathVariable("postId") Integer postId,@PathVariable("tagId") Integer tagId){
		Post post = postService.findOne(postId);
		Tag tag = tagService.findOne(tagId);
		
		if(post == null || tag == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}
		
		post.getTags().add(tag);
		tag.getPosts().add(post);
		
		
		post = postService.save(post);
		tag = tagService.save(tag);
		return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id){
		Post post = postService.findOne(id);
		if(post != null) {
			postService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
