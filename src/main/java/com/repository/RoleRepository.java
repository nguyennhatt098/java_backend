package com.repository;

import java.util.List;
import org.hibernate.Session;

import com.entities.Actions;

public class RoleRepository<T> extends RepositoryBase<T> {

	public RoleRepository(Class<T> clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public List<Actions> getActionByRole(int roleId) {
		Session session = getCurrentSession();
		try {
			return session.createSQLQuery(
							"select ac.* from RoleActions roAc join Actions ac \r\n" + "on roAc.ActionId=ac.Id\r\n"
									+ "join Roles ro\r\n" + "on roAc.RoleId=ro.id\r\n" + "where ro.id = :roleId")
					.setParameter("roleId", roleId).list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return null;
	}

	public List<Actions> getActionByRoleAction(int roleId) {
		Session session = getCurrentSession();
		try {
			return session.createSQLQuery("select * from Actions\r\n"
							+ "where Id not in (select ActionId from RoleActions where RoleId = :roleId)")
					.setParameter("roleId", roleId).list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return null;
	}
}
