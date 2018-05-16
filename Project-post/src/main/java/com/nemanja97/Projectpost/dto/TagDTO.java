package com.nemanja97.Projectpost.dto;

import java.io.Serializable;

import com.nemanja97.Projectpost.entity.Tag;

public class TagDTO implements Serializable {
	
	private int id;
	private String name;
	
	public TagDTO() {
		super();
	}

	public TagDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public TagDTO(Tag tag) {
		this(tag.getId(), tag.getName());
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
	
	
	
}
