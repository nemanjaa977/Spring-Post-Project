package com.nemanja97.Projectpost.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id",unique=true,nullable=false)
	private Integer id;
	@Column(name="title",nullable=false)
	private String title;
	@Column(name="description",nullable=false)
	private String description;
	@Lob
	@Column(name="photo")
	private byte[] photo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date",nullable=false)
	private Date date;
	@Column(name="likes",nullable=false)
	private Integer like;
	@Column(name="dislikes",nullable=false)
	private Integer dislike;
	@Column(name="longitude",nullable=false)
	private float longitude;
	@Column(name="latitude",nullable=false)
	private float latitude;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="user_id",nullable=false)
	private User user;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="post")
	private Set<Comment> comments=new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "post_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();
	
	public Post() {

	}
	
	public Post(Integer id, String title, String description, byte[] photo, Date date, Integer like, Integer dislike, float longitude,
			float latitude, User user, Set<Comment> comments, Set<Tag> tags) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.date = date;
		this.like = like;
		this.dislike = dislike;
		this.longitude = longitude;
		this.latitude = latitude;
		this.user = user;
		this.comments = comments;
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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
	
	
	
}
