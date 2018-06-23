package com.nemanja97.Projectpost.dto;

import java.io.Serializable;

import com.nemanja97.Projectpost.entity.User;

public class UserDTO implements Serializable {
	
	private int id;
	private String name;
	private String username;
	private String password;
	private byte[] photo;
	private String role;
	
	public UserDTO() {
		super();
	}

	public UserDTO(int id, String name, String username, String password, byte[] photo, String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.role = role;
	}
	
	public UserDTO(User user) {
		this(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getPhoto(), user.getRole()); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
