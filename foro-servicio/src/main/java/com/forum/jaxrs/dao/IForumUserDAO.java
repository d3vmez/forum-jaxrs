package com.forum.jaxrs.dao;

import java.util.List;
import java.util.Optional;

import com.forum.jaxrs.model.ForumUser;

public interface IForumUserDAO {
	
	// Método para encontrar un usuario por su id
	public Optional<ForumUser> getById(int id);
	
	// Método para encontrar todos los usuarios
	public List<ForumUser> getAll();
	
	// Método para guardar un usuario
	public ForumUser save(ForumUser assignment);
	
	// Método para eliminar un usuario
	public void deleteById(int id);

}
