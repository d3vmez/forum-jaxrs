package com.forum.jaxrs.dao;

import java.util.List;
import java.util.Optional;

import com.forum.jaxrs.model.ForumCategory;

public interface IForumCategoryDAO {
	
	// Método para encontrar una categoria por su id
	public Optional<ForumCategory> getById(int id);
		
	// Método para encontrar todas las categorias
	public List<ForumCategory> getAll();
	
	// Método para guardar una categoria
	public ForumCategory save(ForumCategory category);
	
	// Método para eliminar una categoria
	public void deleteById(int id);

}
