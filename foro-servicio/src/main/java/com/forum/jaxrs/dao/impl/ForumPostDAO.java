package com.forum.jaxrs.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.forum.jaxrs.dao.IForumPostDAO;
import com.forum.jaxrs.model.ForumCategory;
import com.forum.jaxrs.model.ForumPost;
import com.forum.jaxrs.model.ForumUser;
import com.forum.jaxrs.util.HibernateUtil;

public class ForumPostDAO implements IForumPostDAO{
	
	private Logger log = Logger.getLogger(this.getClass());
	private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	private static Session session = null;

	@Override
	public Optional<ForumPost> getById(int id) {
		ForumPost forumPost = null;
		Optional<ForumPost> optional = null;

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			forumPost = (ForumPost) session.get(ForumPost.class, id);

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		if (forumPost == null) {
			
			log.warn("Not found todo with id: " + id);
			return Optional.empty();
		}

		optional = Optional.of(forumPost);

		return optional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getAllByCategory(ForumCategory forumCategory) {
		List<ForumPost> posts = new ArrayList<>();

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();

			posts = (List<ForumPost>) session
					.createQuery("FROM ForumPost a WHERE a.forumCategory Like :forumCategory")
					.setParameter("forumCategory", forumCategory).list();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return posts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getAllByUser(ForumUser forumUser) {
		List<ForumPost> posts = new ArrayList<>();

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();

			posts = (List<ForumPost>) session
					.createQuery("FROM ForumPost a WHERE a.forumUser Like :forumUser")
					.setParameter("forumUser", forumUser).list();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return posts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getAll() {
		List<ForumPost> posts = new ArrayList<>();

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();

			posts = (List<ForumPost>) session.createQuery("FROM ForumPost f ").list();

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return posts;
	}

	@Override
	public ForumPost save(ForumPost forumPost) {
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
				
			session.saveOrUpdate(forumPost);

			session.getTransaction().commit();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return forumPost;
	}

	@Override
	public void deleteById(int id) {
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			session.createQuery("DELETE FROM ForumPost f WHERE f.id = :id").setParameter("id", id).executeUpdate();
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
