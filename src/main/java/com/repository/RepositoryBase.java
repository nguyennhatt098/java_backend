package com.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.scheduling.annotation.Async;

import com.entities.AnswerComments;
import com.entities.Products;
import com.viewmodel.ActionItem;
import com.viewmodel.ActionResponse;
import com.viewmodel.FieldFilter;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public abstract class RepositoryBase<T> {
	private Class<T> clazz;

	public RepositoryBase(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T getById(int id) {
		Session session = getCurrentSession();
		try {
			return (T) session.get(clazz, id);

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			session.close();
		}

	}
	@Transactional
	public Query findByQuery(String query) {
		Session session = getCurrentSession();
			return session.createQuery(query);
	}
	@Transactional
	public Query findBySqlQuery(String query) {
		Session session = getCurrentSession();
			return session.createSQLQuery(query);
	}

	public List<T> getAll() {
		Session session = getCurrentSession();
		try {
			return session.createQuery("from " + this.clazz.getName()).list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			session.close();
		}
	}

	public SearchResponse<T> searhByText(SearchRequest<T> request, String fields, Session session) {
		String[] fiedList = fields.split(",");
		long count = 0;
		List<T> list = new ArrayList<T>();
		String query = "";
		switch (fiedList.length) {
		case 1:
			query = "from " + this.clazz.getName() + " where " + fiedList[0] + " like :key order by "
					+ request.getOrderBy() + " " + request.getDirection();
			count = (long) session.createQuery(
							"select count(*) from " + this.clazz.getName() + " where " + fiedList[0] + " like :key")
					.setParameter("key", "%" + request.getSearchText() + "%").uniqueResult();
			break;
		case 2:
			query = "from " + this.clazz.getName() + " where " + fiedList[0] + " like :key or " + fiedList[1]
					+ " like :key order by " + request.getOrderBy() + " " + request.getDirection();
			count = (long) session.createQuery("select count(*) from " + this.clazz.getName() + " where " + fiedList[0]
							+ " like :key or " + fiedList[1] + " like :key")
					.setParameter("key", "%" + request.getSearchText() + "%").uniqueResult();
			break;
		case 3:
			query = "from " + this.clazz.getName() + " where " + fiedList[0] + " like :key or " + fiedList[1]
					+ " like :key or " + fiedList[2] + " like :key order by " + request.getOrderBy() + " "
					+ request.getDirection();
			count = (long) session.createQuery("select count(*) from " + this.clazz.getName() + " where " + fiedList[0]
							+ " like :key or " + fiedList[1] + " like :key or " + fiedList[2] + " like :key")
					.setParameter("key", "%" + request.getSearchText() + "%").uniqueResult();
			break;
		default:
			break;
		}

		if (count > 0) {
			list = session.createQuery(query).setParameter("key", "%" + request.getSearchText() + "%")
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();

		}
		return new SearchResponse<T>(count, list, "", true);
	}

	@Async
	public SearchResponse<T> Paging(SearchRequest<T> request, String fields) {
		Session session = getCurrentSession();
		try {
			long count = 0;
			List<T> list = new ArrayList<T>();

			if (request.getSearchText() != "") {
				return searhByText(request, fields, session);
			}
			SearchResponse<T> searchByField = searchByField(request, session);
			count = searchByField.getTotalRecords();
			list = searchByField.getItems();
			if (searchByField.getStatus()) {
				String test="select count(*) from " + this.clazz.getName();
				count = (long) findByQuery("select count(*) from " + this.clazz.getName())
						.uniqueResult();
				list = getCurrentSession()
						.createQuery("from " + this.clazz.getName() + " as table order by table." + request.getOrderBy()
								+ " " + request.getDirection())
						.setFirstResult(request.getPageIndex() * request.getPageSize())
						.setMaxResults(request.getPageSize()).list();
			}
			return new SearchResponse<T>(count, list, "", true);
		} catch (Exception e) {
			// TODO: handle exception
			return new SearchResponse<T>(0, null, e.getMessage(),false );
		} finally {
			session.close();
		}
	}

	private SearchResponse<T> searchByField(SearchRequest<T> request, Session session) {
		Field[] type = request.getItemFilter().getClass().getDeclaredFields();
		long count = 0;
		List<FieldFilter> arr = new ArrayList<>();
		List<T> list = new ArrayList<T>();
		for (Field field : type) {
			String name = field.getName();
			try {
				String value = (String) field.get(request.getItemFilter());
				if (value.length() > 0) {
					FieldFilter fieldFilter = new FieldFilter(name, (String) value);
					arr.add(fieldFilter);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		String query;
		switch (arr.size()) {
		case 0:
			return new SearchResponse<T>(count, list, "", true);
		case 1:
			query = "from " + this.clazz.getName() + " where " + arr.get(0).getName() + " like ?1 order by "
					+ request.getOrderBy() + " " + request.getDirection();
			count = (long) session.createQuery(
					"select count(*) from " + this.clazz.getName() + " where " + arr.get(0).getName() + " like ?1")
					.setParameter(1, "%" + arr.get(0).getValue() + "%").uniqueResult();
			if (count > 0) {
				list = session.createQuery(query).setParameter(1, "%" + arr.get(0).getValue() + "%")
						.setFirstResult(request.getPageIndex() * request.getPageSize())
						.setMaxResults(request.getPageSize()).list();
			}
			break;
		case 2:
			query = "from " + this.clazz.getName() + " where " + arr.get(0).getName() + " like ?1 and "
					+ arr.get(1).getName() + " like ?2 order by " + request.getOrderBy() + " " + request.getDirection();
			count = (long) session
					.createQuery("select count(*) from " + this.clazz.getName() + " where " + arr.get(0).getName()
							+ " like ?1 and " + arr.get(1).getName() + " like ?2")
					.setParameter(1, "%" + arr.get(0).getValue() + "%")
					.setParameter(2, "%" + arr.get(1).getValue() + "%").uniqueResult();
			if (count > 0) {
				list = session.createQuery(query).setParameter(1, "%" + arr.get(0).getValue() + "%")
						.setParameter(2, "%" + arr.get(1).getValue() + "%")
						.setFirstResult(request.getPageIndex() * request.getPageSize())
						.setMaxResults(request.getPageSize()).list();
			}
			break;
		case 3:
			query = "from " + this.clazz.getName() + " where " + arr.get(0).getName() + " like ?1 and "
					+ arr.get(1).getName() + " like ?2 and " + arr.get(2).getName() + " like ?3 order by "
					+ request.getOrderBy() + " " + request.getDirection();
			count = (long) session
					.createQuery("select count(*) from " + this.clazz.getName() + " where " + arr.get(0).getName()
							+ " like ?1 and " + arr.get(1).getName() + " like ?2 and " + arr.get(2).getName()
							+ " like ?3")
					.setParameter(1, "%" + arr.get(0).getValue() + "%")
					.setParameter(2, "%" + arr.get(1).getValue() + "%")
					.setParameter(3, "%" + arr.get(2).getValue() + "%").uniqueResult();
			if (count > 0) {
				list = session.createQuery(query).setParameter(1, "%" + arr.get(0).getValue() + "%")
						.setParameter(2, "%" + arr.get(1).getValue() + "%")
						.setParameter(3, "%" + arr.get(2).getValue() + "%")
						.setFirstResult(request.getPageIndex() * request.getPageSize())
						.setMaxResults(request.getPageSize()).list();
			}
			break;
		case 4:
			query = "from " + this.clazz.getName() + " where " + arr.get(0).getName() + " like ?1 and "
					+ arr.get(1).getName() + " like ?2 and " + arr.get(2).getName() + " like ?3" + arr.get(3).getName()
					+ " like ?4 order by " + request.getOrderBy() + " " + request.getDirection();
			count = (long) session
					.createQuery("select count(*) from " + this.clazz.getName() + " where " + arr.get(0).getName()
							+ " like ?1 and " + arr.get(1).getName() + " like ?2 and " + arr.get(2).getName()
							+ " like ?3" + arr.get(3).getName() + " like ?4")
					.setParameter(1, "%" + arr.get(0).getValue() + "%")
					.setParameter(2, "%" + arr.get(1).getValue() + "%")
					.setParameter(3, "%" + arr.get(2).getValue() + "%")
					.setParameter(4, "%" + arr.get(3).getValue() + "%").uniqueResult();
			if (count > 0) {
				list = session.createQuery(query).setParameter(1, "%" + arr.get(0).getValue() + "%")
						.setParameter(2, "%" + arr.get(1).getValue() + "%")
						.setParameter(3, "%" + arr.get(2).getValue() + "%")
						.setParameter(4, "%" + arr.get(3).getValue() + "%")
						.setFirstResult(request.getPageIndex() * request.getPageSize())
						.setMaxResults(request.getPageSize()).list();
			}
			break;
		}
		return new SearchResponse<T>(count, list, "", false);
	}

	public ActionItem<T> create(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			return new ActionItem<T>(entity, "true");
		} catch (Exception e) {
			session.getTransaction().rollback();
			return new ActionItem<T>(entity, e.getMessage());
		} finally {
			session.close();
		}
	}

	public ActionItem<T> update(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.merge(entity);
			session.getTransaction().commit();
			return new ActionItem<T>(entity, "true");
		} catch (Exception e) {
			return new ActionItem<T>(entity, e.getMessage());
		} finally {
			session.close();
		}
	}

	public ActionItem<T> deleteById(int entityId) {
		T entity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			entity = session.get(clazz, entityId);
			session.delete(entity);
			session.getTransaction().commit();
			return new ActionItem<T>(entity, "true");
		} catch (Exception e) {
			return new ActionItem<T>(entity, e.getMessage());
		} finally {
			session.close();
		}
	}

	public ActionResponse<T> deleteMutiple(List<T> listEntity) {
		ActionResponse<T> res = new ActionResponse();
		List<ActionItem<T>> listSuccess = new ArrayList<ActionItem<T>>();
		List<ActionItem<T>> listErr = new ArrayList<ActionItem<T>>();
		for (T item : listEntity) {
			Field[] type = item.getClass().getDeclaredFields();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				Integer id = (Integer) type[1].get(item);
				session.getTransaction().begin();
				T entity = session.get(clazz, id);
				session.delete(entity);
				session.getTransaction().commit();
				listSuccess.add(new ActionItem<T>(null, "true"));

			} catch (Exception e) {
				listErr.add(new ActionItem<T>(null, e.getMessage()));
			} finally {
				session.close();
			}
		}
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}

	public ActionResponse<T> updateMutiple(List<T> listEntity) {
		ActionResponse<T> res = new ActionResponse();
		List<ActionItem<T>> listSuccess = new ArrayList<ActionItem<T>>();
		List<ActionItem<T>> listErr = new ArrayList<ActionItem<T>>();
		for (T item : listEntity) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.getTransaction().begin();
				session.update(item);
				session.getTransaction().commit();
				listSuccess.add(new ActionItem<T>(null, "true"));

			} catch (Exception e) {
				listErr.add(new ActionItem<T>(null, e.getMessage()));
			} finally {
				session.close();
			}
		}
		res.setFailureItems(listErr);
		res.setSuccessItems(listSuccess);
		return res;
	}
	
	public SearchResponse<T> searchById(SearchRequest<T> request, int id,String countQuery,String listQuery) {
		long count = (long) findByQuery(countQuery)
				.setParameter("id", id)
				.uniqueResult();
		List<T> list = findByQuery(listQuery+
				request.getOrderBy()+" "+request.getDirection()).setParameter("id", id)
				.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
				.list();
		return new SearchResponse<T>(count,list,"true",true);
		}

	public Session getCurrentSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}
}
