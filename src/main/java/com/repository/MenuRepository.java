package com.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.entities.MenuRole;
import com.entities.Menus;

public class MenuRepository<T>  extends RepositoryBase<T>{

	public MenuRepository(Class<T> clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}
	public List<Menus> GetListByRoleId(int roleId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Menus> listMenus = new ArrayList<Menus>();
		try {
			session.getTransaction().begin();
			List<MenuRole> list = session.createQuery("from MenuRole where roleId.id = :id").setParameter("id", roleId).list();
			for (MenuRole menuRole : list) {
				listMenus.add(menuRole.getMenuId());
			}
			return listMenus;
		} catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}
}
