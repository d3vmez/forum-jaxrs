package com.forum.jaxrs.dao;

import java.util.List;
import java.util.Optional;

import com.forum.jaxrs.model.ForumUser;

public interface IForumUserDAO {
	
	// M�todo para encontrar un usuario por su id
	public Optional<ForumUser> getById(int id);
	
	// M�todo para encontrar todos los usuarios
	public List<ForumUser> getAll();
	
	// M�todo para guardar un usuario
	public ForumUser save(ForumUser assignment);
	
	// M�todo para eliminar un usuario
	public void deleteById(int id);

}
