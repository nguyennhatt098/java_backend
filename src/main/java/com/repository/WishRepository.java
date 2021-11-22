package com.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entities.WishLists;

public class WishRepository<T> extends RepositoryBase<T> {

	public WishRepository(Class<T> clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}
	public boolean deleteWish(WishLists w) {
		Session session=getCurrentSession();
		try {
			session.getTransaction().begin();
			Query query= session.createSQLQuery("delete from WishLists where UserID = :userId and ProductID = :productId")
			.setParameter("userId", w.getUser().getId()).setParameter("productId", w.getProduct().getId());
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			return false;
		}
		
	}
}
