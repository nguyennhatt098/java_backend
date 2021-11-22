package com.service;

import java.util.ArrayList;
import java.util.List;

import com.entities.AnswerComments;
import com.entities.Comments;
import com.viewmodel.AnswerCommentViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class AnswerService extends ServiceBase<AnswerComments>{

	public AnswerService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}
	public SearchResponse<AnswerCommentViewModel> searchById(SearchRequest<AnswerComments> request) {
		String countQuery="select count(*) from AnswerComments where commentId = :id";
		String listQuery="from AnswerComments where commentId = :id order by ";
		SearchResponse<AnswerComments> res= _repository.searchById(request, request.getItemFilter().getId(), countQuery, listQuery);
		List<AnswerCommentViewModel> list=new ArrayList<AnswerCommentViewModel>();
		for (AnswerComments item : res.getItems()) {
			AnswerCommentViewModel model=new AnswerCommentViewModel();
			model.setContent(item.getContent());
			model.setId(item.getId());
			model.setFullName(item.getUserId().getFullName());
			model.setImage(item.getUserId().getImage());
			model.setStatus(item.getStatus());
			model.setCreatedDate(item.getCreatedDate());
			list.add(model);
		}
		Comments comment=(Comments)findByQuery("from Comments where id = :id").setParameter("id", request.getItemFilter().getId()).uniqueResult();
		SearchResponse<AnswerCommentViewModel> data=new SearchResponse<AnswerCommentViewModel>();
		data.setItems(list);
		data.setTotalRecords(res.getTotalRecords());
		data.setMessage(comment.getQuestion());
		return data;
		}
}
