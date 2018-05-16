package com.nemanja97.Projectpost.dto;

import java.io.Serializable;
import java.util.Date;

import com.nemanja97.Projectpost.entity.Comment;

public class CommentDTO implements Serializable {
	
	private int id;
	private String title;
	private String description;
	private Date date;
	private int like;
	private int dislike;
	private PostDTO postDTO;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(int id, String title, String description, Date date, int like, int dislike, PostDTO postDTO) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.like = like;
		this.dislike = dislike;
		this.postDTO = postDTO;
	}
	
	public CommentDTO(Comment comment) {
		this(comment.getId(), comment.getTitle(), comment.getDescription(), comment.getDate(), comment.getLike(), comment.getDislike(), new PostDTO(comment.getPost()));
	}
	
	

	public PostDTO getPostDTO() {
		return postDTO;
	}

	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
	
	
}
