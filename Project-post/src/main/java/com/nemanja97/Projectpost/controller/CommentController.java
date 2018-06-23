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
import com.nemanja97.Projectpost.entity.Comment;
import com.nemanja97.Projectpost.service.CommentServiceInterface;
import com.nemanja97.Projectpost.service.PostServiceInterface;
import com.nemanja97.Projectpost.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/comment")
public class CommentController {
	
	@Autowired
	private CommentServiceInterface commentService;
	
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> getComments(){
		List<Comment> comments = commentService.findAll();
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for(Comment c: comments) {
			commentsDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/post/{id}")
	public ResponseEntity<List<CommentDTO>> getCommentById(@PathVariable("id") Integer id){
		List<Comment> comments = commentService.findByPost(id);
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for(Comment c: comments) {
			commentsDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/order/{orderBy}/{postId}")
	public ResponseEntity<List<CommentDTO>> getCommentById(@PathVariable("orderBy") String orderBy, @PathVariable("postId") Integer postId){
		List<Comment> comments = new ArrayList<>();
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		if(orderBy.equals("date")) {
			System.out.println("Hello");
			 comments = commentService.findAllOrderByDate(postId);
		}else if(orderBy.equals("numberLike")) {
			System.out.println("Hello 2");
			comments = commentService.findAllOrderByLike(postId);
		}else if(orderBy.equals("numberDislike")) {
			System.out.println("Hello 3");
			comments = commentService.findAllOrderByDislike(postId);
		}
		
		for(Comment c: comments) {
			commentsDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){
		Comment comment = new Comment();
		comment.setTitle(commentDTO.getTitle());
		comment.setDescription(commentDTO.getDescription());
		comment.setDate(commentDTO.getDate());
		comment.setLike(commentDTO.getLike());
		comment.setDislike(commentDTO.getDislike());
		comment.setUser(userService.findOne(commentDTO.getUserDTO().getId()));
		comment.setPost(postService.findOne(commentDTO.getPostDTO().getId()));
		
		comment = commentService.save(comment);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<CommentDTO> updateComment( @PathVariable("id") Integer id,@RequestBody CommentDTO commentDTO ){
		Comment comment = commentService.findOne(id);
		if(comment == null) {
			return new ResponseEntity<CommentDTO>(HttpStatus.BAD_REQUEST);
		}
		
		comment.setTitle(commentDTO.getTitle());
		comment.setDescription(commentDTO.getDescription());
		comment.setDate(commentDTO.getDate());
		comment.setLike(commentDTO.getLike());
		comment.setDislike(commentDTO.getDislike());
		comment.setPost(postService.findOne(commentDTO.getPostDTO().getId()));
		comment.setUser(userService.findOne(commentDTO.getUserDTO().getId()));
		
		comment = commentService.save(comment);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id){
		Comment comment = commentService.findOne(id);
		if(comment != null) {
			commentService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
