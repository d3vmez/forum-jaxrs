package com.forum.jaxrs.dao;

import java.util.List;
import java.util.Optional;

import com.forum.jaxrs.model.ForumCategory;

public interface IForumCategoryDAO {
	
	// M�todo para encontrar una categoria por su id
	public Optional<ForumCategory> getById(int id);
		
	// M�todo para encontrar todas las categorias
	public List<ForumCategory> getAll();
	
	// M�todo para guardar una categoria
	public ForumCategory save(ForumCategory category);
	
	// M�todo para eliminar una categoria
	public void deleteById(int id);

}
