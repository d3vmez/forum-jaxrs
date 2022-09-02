package com.forum.jaxrs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
public class ForumUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Email
	private String email;
	private String name;
	private String password;
	
	@OneToMany(mappedBy = "forumUser")
	private List<ForumPost> posts;
		
	public ForumUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForumUser(int id, @Email String email, String name, String password, List<ForumPost> posts) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ForumPost> getPosts() {
		return posts;
	}

	public void setPosts(List<ForumPost> posts) {
		this.posts = posts;
	}
	
}
