package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.entities.AnswerComments;
import com.entities.Comments;
import com.entities.Notifies;
import com.entities.NotifiesCustom;
import com.entities.NotifiesUser;
import com.entities.Orders;
import com.entities.ReviewProducts;
import com.viewmodel.ActionItem;
import com.viewmodel.AnswerCommentViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class NotifiesService<T> extends ServiceBase<Notifies> {

	public NotifiesService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public List<Notifies> getListById(int id) {
		return findByQuery("from Notifies where user.id = :id").setParameter("id", id).list();
	}

	public ActionItem<Notifies> UpdateNotifies(Notifies entity) {
		if (entity.getUser().getEmail() != null) {
			entity.setStatus(0);
			return _repository.update(entity);
		} else {
			NotifiesUser notifiesUser = (NotifiesUser) findByQuery(
					"from NotifiesUser where userId.id = :userId and notifiesId.id = :notifiesId")
							.setParameter("userId", entity.getUser().getId())
							.setParameter("notifiesId", entity.getNotifiesId()).uniqueResult();
			notifiesUser.setStatus(0);
			Session session = getCurrentSession();
			session.getTransaction().begin();
			session.update(notifiesUser);
			session.getTransaction().commit();
		}
		return new ActionItem<Notifies>(null, "true");
	}
	public long getTotalNotifies(int userId) {
		long customTotal=(long)findByQuery("select count(*) from NotifiesUser where userId.id = :userId and status=1")
				.setParameter("userId", userId).uniqueResult();
		if(customTotal>0) {
			long notifiesTotal=(long)findByQuery("select count(*) from Notifies where user.id = :userId and status=1")
					.setParameter("userId", userId).uniqueResult();
			customTotal+=notifiesTotal;
		}else {
			return (long)findByQuery("select count(*) from Notifies where user.id = :userId and status=1")
					.setParameter("userId", userId).uniqueResult();
		}
		return customTotal;
	}
}
