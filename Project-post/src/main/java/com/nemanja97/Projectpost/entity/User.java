package com.nemanja97.Projectpost.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",unique=true,nullable=false)
	private Integer id;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="username",nullable=false)
	private String username;
	@Column(name="password",nullable=false)
	private String password;
	@Lob
	@Column(name="photo")
	private byte[] photo;
	@Column(name="role",nullable=true)
	private String role;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="user")
	private Set<Post> posts= new HashSet<Post>();
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="user")
	private Set<Comment>comments=new HashSet<>();
	
	public Set<Post> getPosts() {
		return posts;
	}
	
	public User() {
		
	}
	
	public User(Integer id, String name, String username, String password, byte[] photo, String role, Set<Post> posts,
			Set<Comment> comments) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.role = role;
		this.posts = posts;
		this.comments = comments;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
