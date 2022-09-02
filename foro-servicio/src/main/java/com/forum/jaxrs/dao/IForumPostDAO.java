package com.forum.jaxrs.dao;

import java.util.List;
import java.util.Optional;

import com.forum.jaxrs.model.ForumCategory;
import com.forum.jaxrs.model.ForumPost;
import com.forum.jaxrs.model.ForumUser;

public interface IForumPostDAO {
	
	// M�todo para encontrar un post por su id
	public Optional<ForumPost> getById(int id);
	
	// M�todo para encontrar posts por su categoria
	public List<ForumPost> getAllByCategory(ForumCategory forumCategory); 
	
	// M�todo para encontrar posts por su usuario
	public List<ForumPost> getAllByUser(ForumUser forumUser);
	
	// M�todo para encontrar todas los posts
	public List<ForumPost> getAll();
	
	// M�todo para guardar un post
	public ForumPost save(ForumPost forumPost);
	
	// M�todo para eliminar un post
	public void deleteById(int id);

}
