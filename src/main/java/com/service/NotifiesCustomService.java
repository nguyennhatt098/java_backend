package com.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.scheduling.annotation.Async;

import com.entities.NotifiesCustom;
import com.entities.NotifiesUser;
import com.entities.Users;
import com.viewmodel.ActionItem;
import com.viewmodel.ActionResponse;
import com.viewmodel.FieldFilter;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class NotifiesCustomService extends ServiceBase<NotifiesCustom> {

	public NotifiesCustomService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public SearchResponse<NotifiesCustom> Search(SearchRequest<NotifiesCustom> request) {
		String output = request.getOrderBy().substring(0, 1).toLowerCase() + request.getOrderBy().substring(1);
		request.setOrderBy(output);
		SearchResponse<NotifiesCustom> res = _repository.Paging(request, "content");
		List<NotifiesCustom> list = new ArrayList<NotifiesCustom>();
		for (NotifiesCustom item : res.getItems()) {
			item.setNotifiesUserCollection(null);
			list.add(item);
		}
		res.setItems(list);
		return res;
	}
	public ActionResponse<NotifiesUser> DeleteMutipleNotifies(List<NotifiesUser> list) {
		ActionResponse<NotifiesUser> res = new ActionResponse();
		List<ActionItem<NotifiesUser>> listSuccess = new ArrayList<ActionItem<NotifiesUser>>();
		List<ActionItem<NotifiesUser>> listErr = new ArrayList<ActionItem<NotifiesUser>>();
		for (NotifiesUser item : list) {
			Session session = getCurrentSession();
			try {
				session.getTransaction();
				session.getTransaction().begin();
			Query query=	session.createSQLQuery("delete from NotifiesUser where [UserId] = :userId and [NotifiesId] = :notifyId")
				.setParameter("userId", item.getUserId().getId())
				.setParameter("notifyId", item.getNotifiesId().getId());
			query.executeUpdate();
				listSuccess.add(new ActionItem<NotifiesUser>(null, "true"));

			} catch (Exception e) {
				listErr.add(new ActionItem<NotifiesUser>(null, e.getMessage()));
			} finally {
				session.close();
			}
		}
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}

	public ActionResponse<NotifiesUser> addMutipleNotifies(List<NotifiesUser> list) {
		ActionResponse<NotifiesUser> res = new ActionResponse();
		List<ActionItem<NotifiesUser>> listSuccess = new ArrayList<ActionItem<NotifiesUser>>();
		List<ActionItem<NotifiesUser>> listErr = new ArrayList<ActionItem<NotifiesUser>>();
		for (NotifiesUser item : list) {
			Session session = getCurrentSession();
			try {
				session.getTransaction().begin();
				item.setStatus(1);
				session.save(item);
				session.getTransaction().commit();
				listSuccess.add(new ActionItem<NotifiesUser>(null, "true"));

			} catch (Exception e) {
				listErr.add(new ActionItem<NotifiesUser>(null, e.getMessage()));
			} finally {
				session.close();
			}
		}
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}

	public List<NotifiesCustom> getNotifiesCustomById(int id) {
		List<NotifiesUser> notifiesUser = findByQuery("from NotifiesUser where userId.id = :id").setParameter("id", id)
				.list();
		List<NotifiesCustom> list = new ArrayList<NotifiesCustom>();
		for (NotifiesUser item : notifiesUser) {
			NotifiesCustom notifiesCustom = item.getNotifiesId();
			notifiesCustom.setStatus(item.getStatus());
			notifiesCustom.setNotifiesId(item.getNotifiesId().getId());
			notifiesCustom.setNotifiesUserCollection(null);
			list.add(notifiesCustom);
		}
		return list;
	}

	public SearchResponse<Users> searhByTextNotExist(SearchRequest<Users> request, String fields, Session session) {

		String query = "select  u.[UserName],u.[FullName],u.[Address],u.[Image],u.Id,u.[Status] from Users u where (userName like :key or fullName like :key  or address like :key) "
				+ "and not EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)";
		int count = (int) session.createSQLQuery(
				"select count(u.Id) from Users u where (userName like :key or fullName like :key  or address like :key) "
						+ "and not EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
				.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
				.setParameter("key", "%" + request.getSearchText() + "%").uniqueResult();

		List<Users> users = new ArrayList<Users>();
		if (count > 0) {
			List<Object[]> list = session.createSQLQuery(query).setParameter("key", "%" + request.getSearchText() + "%")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			users = convertUser(list);
		}
		return new SearchResponse<Users>(count, users, "", true);
	}

	private List<Users> convertUser(List<Object[]> list) {
		List<Users> users = new ArrayList<Users>();
		for (int i = 0; i < list.size(); i++) {
			Users u = new Users();
			u.setUserName(list.get(i)[0] + "");
			u.setAddress(list.get(i)[2] + "");
			u.setImage(list.get(i)[3] + "");
			u.setFullName(list.get(i)[1] + "");
			u.setId(Integer.parseInt(list.get(i)[4].toString()));
			u.setStatus(Boolean.parseBoolean(list.get(i)[5].toString()));
			users.add(u);
		}
		return users;
	}

	
	@Async
	public SearchResponse<Users> PagingNotExistUser(SearchRequest<Users> request) {
		Session session = getCurrentSession();
		try {
			if (request.getSearchText() != "") {
				return searhByTextNotExist(request, "userName,fullName,address", session);
			}
			long count = 0;
			count = (int) session.createSQLQuery(
					"select count(u.Id) from Users u where not EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId()).uniqueResult();
			List<Object[]> list = session.createSQLQuery(
					"select u.[UserName],u.[FullName],u.[Address],u.[Image],u.Id,u.[Status] from Users u where not EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			List<Users> users = convertUser(list);

			return new SearchResponse<Users>(count, users, "", true);
		} catch (Exception e) {
			// TODO: handle exception
			return new SearchResponse<Users>(0, null, e.getMessage(), false);
		} finally {
			session.close();
		}
	}
	@Async
	public SearchResponse<Users> PagingExistUser(SearchRequest<Users> request) {
		Session session = getCurrentSession();
		try {
			if (request.getSearchText() != "") {
				return searhByTextExist(request, "userName,fullName,address", session);
			}
			long count = 0;
			count = (int) session.createSQLQuery(
					"select count(u.Id) from Users u where  EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId()).uniqueResult();
			List<Object[]> list = session.createSQLQuery(
					"select u.[UserName],u.[FullName],u.[Address],u.[Image],u.Id,u.[Status] from Users u where  EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			List<Users> users = convertUser(list);

			return new SearchResponse<Users>(count, users, "", true);
		} catch (Exception e) {
			// TODO: handle exception
			return new SearchResponse<Users>(0, null, e.getMessage(), false);
		} finally {
			session.close();
		}
	}
	public SearchResponse<Users> searhByTextExist(SearchRequest<Users> request, String fields, Session session) {

		String query = "select  u.[UserName],u.[FullName],u.[Address],u.[Image],u.Id,u.[Status] from Users u where (userName like :key or fullName like :key  or address like :key) "
				+ "and  EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)";
		int count = (int) session.createSQLQuery(
				"select count(u.Id) from Users u where (userName like :key or fullName like :key  or address like :key) "
						+ "and  EXISTS (select 1 from NotifiesUser n where u.Id=n.UserId and n.NotifiesId = :notifiesId)")
				.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
				.setParameter("key", "%" + request.getSearchText() + "%").uniqueResult();

		List<Users> users = new ArrayList<Users>();
		if (count > 0) {
			List<Object[]> list = session.createSQLQuery(query).setParameter("key", "%" + request.getSearchText() + "%")
					.setParameter("notifiesId", request.getItemFilter().getNotifiesId())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			users = convertUser(list);
		}
		return new SearchResponse<Users>(count, users, "", true);
	}

}
