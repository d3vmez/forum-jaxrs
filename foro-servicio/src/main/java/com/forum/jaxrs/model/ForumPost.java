package com.forum.jaxrs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class ForumPost implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @ManyToOne()
    @JoinColumn(name = "fk_category")
	private ForumCategory forumCategory;
    
    @ManyToOne()
    @JoinColumn(name = "fk_user")
	private ForumUser forumUser;
    
    private String subject;
    private String message;
    
	public ForumPost(int id, ForumCategory categories, ForumUser forumUser, String subject, String message) {
		super();
		this.id = id;
		this.forumCategory = categories;
		this.forumUser = forumUser;
		this.subject = subject;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ForumCategory getCategories() {
		return forumCategory;
	}

	public void setCategories(ForumCategory forumCategory) {
		this.forumCategory = forumCategory;
	}

	public ForumUser getUser() {
		return forumUser;
	}

	public void setUser(ForumUser forumUser) {
		this.forumUser = forumUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
