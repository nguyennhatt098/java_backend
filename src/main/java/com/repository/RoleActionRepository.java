package com.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import com.entities.MenuRole;
import com.entities.Menus;
import com.entities.RoleActions;
import com.viewmodel.ActionItem;
import com.viewmodel.ActionResponse;

public class RoleActionRepository<T> extends RepositoryBase<T> {

	public RoleActionRepository(Class<T> clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}
	public ActionItem<T> deleteByActionId(int entityId) {
		T entity = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			entity = (T)session.createQuery("from RoleActions as r where r.actionId = :id").setParameter("id", entityId).uniqueResult();
			session.delete(entity);
			session.getTransaction().commit();
			return new ActionItem<T>(entity, "true");
		} catch (Exception e) {
			return new ActionItem<T>(entity, e.getMessage());
		}
	}
	public ActionResponse<RoleActions> deleteMutipleRoleAction(List<RoleActions> listEntity) {
		ActionResponse<RoleActions> res = new ActionResponse();
		List<ActionItem<RoleActions>> listSuccess = new ArrayList<ActionItem<RoleActions>>();
		List<ActionItem<RoleActions>> listErr = new ArrayList<ActionItem<RoleActions>>();
		for (RoleActions item : listEntity) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.getTransaction().begin();
				if(item.getActionId().getActionName().contains("VIEW")) {
					Query query = session.createSQLQuery("delete from  MenuRole where RoleId = :roleId and actionName = :actionName")
							.setParameter("roleId", item.getRoleId().getId())
							.setParameter("actionName", item.getActionId().getActionName());
					query.executeUpdate();
				}
					Query query = session.createSQLQuery("delete from  RoleActions where RoleId = :roleId and ActionId = :actionId")
							.setParameter("roleId", item.getRoleId().getId())
							.setParameter("actionId", item.getActionId().getId());
					query.executeUpdate();
					listSuccess.add(new ActionItem<RoleActions>(item, "true"));
				
			} catch (Exception e) {
				
				listErr.add(new ActionItem<RoleActions>(item, e.getMessage()));
			} finally {
				session.close();
			}
		}
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}
	public ActionResponse<RoleActions> createMutiple(List<RoleActions> listEntity) {
		ActionResponse<RoleActions> res = new ActionResponse();
		List<ActionItem<RoleActions>> listSuccess = new ArrayList<ActionItem<RoleActions>>();
		List<ActionItem<RoleActions>> listErr = new ArrayList<ActionItem<RoleActions>>();
		for (RoleActions item : listEntity) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.getTransaction().begin();
				if(item.getActionId().getActionName().contains("VIEW")) {
					MenuRole menuRole=new MenuRole();
					menuRole.setActionName(item.getActionId().getActionName());
					Menus menu=(Menus)session.createQuery("from Menus where actionName = :actionName")
							.setParameter("actionName", item.getActionId().getActionName()).uniqueResult();
					menuRole.setMenuId(menu);
					menuRole.setRoleId(item.getRoleId());
					menuRole.setActionName(item.getActionId().getActionName());
					session.save(menuRole);
				}
				session.save(item);
				session.getTransaction().commit();
				listSuccess.add(new ActionItem<RoleActions>(item, "true"));
			} catch (Exception e) {
				listErr.add(new ActionItem<RoleActions>(item, e.getMessage()));
			}finally {
				session.close();
			}
		}
		
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}
}
