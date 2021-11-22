package com.service;

import java.util.ArrayList;
import java.util.List;

import com.entities.Categories;
import com.entities.Comments;
import com.entities.Products;
import com.viewmodel.CommentViewModel;
import com.viewmodel.ProductViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class CommentService extends ServiceBase<Comments>{

	public CommentService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}
	public SearchResponse<CommentViewModel> searchCommentById(SearchRequest<Comments> request) {
		SearchResponse<Comments> res = new SearchResponse<Comments>();
		if (request.getItemFilter().getId() == null) {
			res = _repository.Paging(request, "name");
		} else {
			long count = (long) _repository.findByQuery("select count(*) from Comments where product.id = :id")
					.setParameter("id", request.getItemFilter().getId()).uniqueResult();
			List<Comments> list = _repository.findByQuery("from Comments where product.id = :id order by "
					+request.getOrderBy()+" "+request.getDirection())
					.setParameter("id", request.getItemFilter().getId())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			res.setItems(list);
			res.setTotalRecords(count);
		}
		List<CommentViewModel> list=new ArrayList<CommentViewModel>();
		for (Comments item : res.getItems()) {
			CommentViewModel cm=new CommentViewModel();
			cm.setCreatedDate(item.getCreatedDate());
			cm.setId(item.getId());
			cm.setImage(item.getUserId().getImage());
			cm.setQuestion(item.getQuestion());
			cm.setStatus(item.getStatus());
			cm.setUserId(item.getUserId().getId());
			cm.setUserName(item.getUserId().getUserName());
			list.add(cm);
		}
		SearchResponse<CommentViewModel> data = new SearchResponse<CommentViewModel>();
		data.setItems(list);
		data.setTotalRecords(res.getTotalRecords());
		return data;
	}
}
