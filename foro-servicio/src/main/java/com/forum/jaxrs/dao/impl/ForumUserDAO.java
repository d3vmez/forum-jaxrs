package com.forum.jaxrs.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.forum.jaxrs.dao.IForumUserDAO;
import com.forum.jaxrs.model.ForumUser;
import com.forum.jaxrs.util.HibernateUtil;

public class ForumUserDAO implements IForumUserDAO{
	
	private Logger log = Logger.getLogger(this.getClass());
	private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	private static Session session = null;

	@Override
	public Optional<ForumUser> getById(int id) {
		ForumUser forumUser = null;
		Optional<ForumUser> optional = null;

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			forumUser = (ForumUser) session.get(ForumUser.class, id);

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		if (forumUser == null) {
			
			log.warn("Not found todo with id: " + id);
			return Optional.empty();
		}

		optional = Optional.of(forumUser);

		return optional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumUser> getAll() {
		List<ForumUser> users = new ArrayList<>();

		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();

			users = (List<ForumUser>) session.createQuery("FROM ForumUser f ").list();

		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return users;
	}

	@Override
	public ForumUser save(ForumUser forumUser) {
		
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
				
			session.saveOrUpdate(forumUser);

			session.getTransaction().commit();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return forumUser;
	}

	@Override
	public void deleteById(int id) {
		
		try {
			// Obtener el objeto Session de SessionFactory
			session = sessionFactory.openSession();

			// Empezar transacción
			session.beginTransaction();
			session.createQuery("DELETE FROM ForumUser f WHERE f.id = :id").setParameter("id", id).executeUpdate();
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


