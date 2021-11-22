package com.repository;

import org.hibernate.Session;

import com.entities.Users;

public class UserRepository<T> extends RepositoryBase<T> {

	public UserRepository(Class<T> clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public Users authenticate(Users u) {
		Session session = getCurrentSession();
		try {
			Users user = (Users) session.createQuery("from Users where userName = :userName and password = :pass")
					.setParameter("userName", u.getUserName()).setParameter("pass", u.getPassword()).uniqueResult();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return null;
	}
	public boolean checkActionByUserId(int userId,String actionName) {
		Session session = getCurrentSession();
		try {
			return  session.createSQLQuery("select u.Id from Roles r \r\n" + 
					"join Users u  on  r.Id=u.RoleId\r\n" + 
					"join RoleActions roAc on r.Id=roAc.RoleId\r\n" + 
					"join Actions a on roAc.ActionId=a.Id\r\n" + 
					"where u.Id = :userId and a.ActionName = :actionName")
					.setParameter("userId",userId).setParameter("actionName",actionName).uniqueResult()!=null;
			
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
	}
}
