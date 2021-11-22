package com.service;

import java.util.List;

import org.hibernate.query.Query;

import com.entities.Categories;
import com.repository.CategoryRepository;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class CategoryService extends ServiceBase<Categories> {
	CategoryRepository<Categories> _categoryRepository;

	public CategoryService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
		_categoryRepository = new CategoryRepository(Categories.class);
	}

	public List<Categories> findAll() {

		return _categoryRepository.findByQuery("from Categories").list();
	}

	public List<Categories> SeachDropDown(String searchText) {
		Query<Categories> query;
		if (searchText == null || searchText == "") {
			query = _categoryRepository.findByQuery("from Categories");
		} else {
			query = _categoryRepository.findByQuery("from Categories where name like :key").setParameter("key",
					searchText);
		}
		return query.list();
	}

	public SearchResponse<Categories> Search(SearchRequest<Categories> request) {
		String output = request.getOrderBy().substring(0, 1).toLowerCase() + request.getOrderBy().substring(1);
		request.setOrderBy(output);
		return _categoryRepository.Paging(request, "name,slug");
	}
	public List<Categories> getFeatureCategory() {
		return _categoryRepository.findByQuery("from Categories where id in (select categories.id from Products)").list();
	}
}
