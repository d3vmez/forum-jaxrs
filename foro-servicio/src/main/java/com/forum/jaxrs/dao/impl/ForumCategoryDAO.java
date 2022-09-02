package com.forum.jaxrs.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.forum.jaxrs.dao.IForumCategoryDAO;
import com.forum.jaxrs.model.ForumCategory;
import com.forum.jaxrs.util.HibernateUtil;

public class ForumCategoryDAO implements IForumCategoryDAO{
	
	private Logger log = Logger.getLogger(this.getClass());
	private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	private static Session session = null;

	@Override
	public Optional<ForumCategory> getById(int id) {
		ForumCategory forumCategory = null;
		Optional<ForumCategory> optional = null;

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			forumCategory = (ForumCategory) session.get(ForumCategory.class, id);

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		if (forumCategory == null) {
			
			log.warn("Not found todo with id: " + id);
			return Optional.empty();
		}

		optional = Optional.of(forumCategory);

		return optional;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumCategory> getAll() {
		List<ForumCategory> categories = new ArrayList<>();

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();

			categories = (List<ForumCategory>) session.createQuery("FROM ForumCategory f ").list();

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return categories;
	}

	@Override
	public ForumCategory save(ForumCategory category) {
		
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
				
			session.saveOrUpdate(category);

			session.getTransaction().commit();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return category;
	}

	@Override
	public void deleteById(int id) {
		
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			session.createQuery("DELETE FROM ForumCategory f WHERE f.id = :id").setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}
		
	}

}
