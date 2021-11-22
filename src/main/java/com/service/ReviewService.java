package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.entities.AnswerComments;
import com.entities.AnswerReviews;
import com.entities.Comments;
import com.entities.Notifies;
import com.entities.Orders;
import com.entities.Products;
import com.entities.ReviewProducts;
import com.viewmodel.ActionItem;
import com.viewmodel.AnswerCommentViewModel;
import com.viewmodel.CommentViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class ReviewService extends ServiceBase<ReviewProducts> {

	public ReviewService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public boolean createReview(List<ReviewProducts> list) {
			boolean checkExistOrder=findByQuery("from Orders where verifyCode = :verifyCode")
			.setParameter("verifyCode", list.get(0).getOrder().getVerifyCode()).uniqueResult()!=null;
		if(!checkExistOrder) return false;
		Session session = getCurrentSession();
		
		try {
			session.getTransaction().begin();
			for (ReviewProducts item : list) {
				session.save(item);
				session.merge(getProduct(item, session));
			}
			Orders order=(Orders)findByQuery("from Orders where id = :id").setParameter("id", list.get(0).getOrder().getId()).uniqueResult();
			Notifies notify=(Notifies)findByQuery("from Notifies where link = :verifyCode").setParameter("verifyCode", order.getVerifyCode()).uniqueResult();
			order.setVerifyCode(null);
			session.update(order);
			session.delete(notify);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public Products getProduct(ReviewProducts item, Session session) {

		float star1 = 0;
		float star2 = 0;
		float star3 = 0;
		float star4 = 0;
		float star5 = 0;
		
		List<ReviewProducts> dataReviews = session
				.createQuery("from ReviewProducts where product.id =" + item.getProduct().getId()).list();
		for (ReviewProducts review : dataReviews) {

			switch (review.getRate()) {
			case 1:
				star1 += 1;
				break;
			case 2:
				star2 += 1;
				break;
			case 3:
				star3 += 1;
				break;
			case 4:
				star4 += 1;
				break;
			default:
				star5 += 1;
				break;
			}
		}
		Products p = (Products) session.createQuery("from Products where id = :id")
				.setParameter("id", item.getProduct().getId()).uniqueResult();
		star1 = Math.abs((star1 / dataReviews.size()) * 100);
		star2 = Math.abs((star2 / dataReviews.size()) * 100);
		star3 = Math.abs((star3 / dataReviews.size()) * 100);
		star4 = Math.abs((star4 / dataReviews.size()) * 100);
		star5 = Math.abs((star5 / dataReviews.size()) * 100);
		p.setStar1(star1);
		p.setStar2(star2);
		p.setStar3(star3);
		p.setStar4(star4);
		p.setStar5(star5);
		float average = (p.getStar1() + p.getStar2() * 2 + p.getStar3() * 3 + p.getStar4() * 4 + p.getStar5() * 5)
				/ (p.getStar1() + p.getStar2() + p.getStar3() + p.getStar4() + p.getStar5());
		p.setAverageStar(average);
		return p;
	}
	
	public SearchResponse<CommentViewModel> searchById(SearchRequest<ReviewProducts> request) {
		String countQuery="select count(*) from ReviewProducts where product.id = :id";
		String listQuery="from ReviewProducts where product.id = :id order by ";
		SearchResponse<ReviewProducts> res= _repository.searchById(request, request.getItemFilter().getId(), countQuery, listQuery);
		List<CommentViewModel> list=new ArrayList<CommentViewModel>();
		for (ReviewProducts item : res.getItems()) {
			CommentViewModel model=new CommentViewModel();
			model.setQuestion(item.getContent());
			model.setId(item.getId());
			model.setUserName(item.getOrder().getUserId().getFullName());
			model.setImage(item.getOrder().getUserId().getImage());
			model.setStatus(item.getStatus());
			model.setRate(item.getRate());
			model.setCreatedDate(item.getCreatedDate());
			list.add(model);
		}
//		ReviewProducts comment=(ReviewProducts)findByQuery("from ReviewProducts where product.id = :id").setMaxResults(1).setParameter("id", request.getItemFilter().getId()).uniqueResult();
		SearchResponse<CommentViewModel> data=new SearchResponse<CommentViewModel>();
		data.setItems(list);
		data.setTotalRecords(res.getTotalRecords());
//		data.setMessage(comment.getContent());
		return data;
		}
	public ActionItem<AnswerReviews> createAnswer(AnswerReviews item){
		Session session=getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(item);
			session.getTransaction().commit();
			return new ActionItem<AnswerReviews>(null, "true");
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			return new ActionItem<AnswerReviews>(null, e.getMessage());
		}
		
	}
	public SearchResponse<AnswerCommentViewModel> searchAnswerReviewById(SearchRequest<AnswerComments> request) {
		long count=(long)findByQuery("select count(*) from AnswerReviews where reviewProduct.id = :id").setParameter("id", request.getItemFilter().getId()).uniqueResult();
		List<AnswerReviews> listItem=findByQuery("from AnswerReviews where reviewProduct.id = :id order by "+request.getOrderBy()+" "+request.getDirection())
				.setParameter("id", request.getItemFilter().getId())
				.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize()).list();
		List<AnswerCommentViewModel> list=new ArrayList<AnswerCommentViewModel>();
		for (AnswerReviews item : listItem) {
			AnswerCommentViewModel model=new AnswerCommentViewModel();
			model.setContent(item.getContent());
			model.setId(item.getId());
			model.setFullName(item.getUser().getFullName());
			model.setImage(item.getUser().getImage());
			model.setStatus(item.getStatus());
			model.setCreatedDate(item.getCreatedDate());
			list.add(model);
		}
		ReviewProducts review=(ReviewProducts)findByQuery("from ReviewProducts where id = :id").setParameter("id", request.getItemFilter().getId()).uniqueResult();
		SearchResponse<AnswerCommentViewModel> data=new SearchResponse<AnswerCommentViewModel>();
		data.setItems(list);
		data.setTotalRecords(count);
		data.setMessage(review.getContent());
		return data;
		}
}
