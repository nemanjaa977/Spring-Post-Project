package com.nemanja97.Projectpost.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id",unique=true,nullable=false)
	private Integer id;
	@Column(name="title",nullable=false)
	private String title;
	@Column(name="description",nullable=false)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date",nullable=false)
	private Date date;
	@Column(name="likes",nullable=false)
	private Integer like;
	@Column(name="dislikes",nullable=false)
	private Integer dislike;
	
	@ManyToOne
	@JoinColumn(name="post_id",referencedColumnName="post_id",nullable=false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="user_id",nullable=false)
	private User user;
	
	public Comment() {
		
	}
	
	public Comment(Integer id, String title, String description, Date date, Integer like, Integer dislike, Post post, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.like = like;
		this.dislike = dislike;
		this.post = post;
		this.user = user;
	}


	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getDislike() {
		return dislike;
	}
	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}
	
	
	
}
