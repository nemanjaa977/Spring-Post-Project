package com.nemanja97.Projectpost.dto;

import java.io.Serializable;
import java.util.Date;

import com.nemanja97.Projectpost.entity.Post;

public class PostDTO implements Serializable {
	
	private int id;
	private String title;
	private String description;
	private byte[] photo;
	private Date date;
	private int like;
	private int dislike;
	private UserDTO userDTO;
	private float longitude;
	private float latitude;
	
	public PostDTO(int id, String title, String description, byte[] photo, Date date, int like, int dislike, UserDTO userDTO,float longitude,float latitude) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.date = date;
		this.like = like;
		this.dislike = dislike;
		this.userDTO = userDTO;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public PostDTO(Post post) {
		this(post.getId(), post.getTitle(), post.getDescription(), post.getPhoto(), post.getDate(), post.getLike(), post.getDislike(), new UserDTO(post.getUser()), post.getLongitude(), post.getLatitude());
	}
	
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public PostDTO() {
		super();
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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
