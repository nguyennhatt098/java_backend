package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.scheduling.annotation.Async;

import com.entities.AnswerComments;
import com.entities.Products;
import com.repository.ProductRepository;
import com.viewmodel.ProductViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;
import com.viewmodel.ValidateResponse;

public class ProductService extends ServiceBase<Products> {
	public ProductService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
		_productRepository = new ProductRepository(Products.class);

	}

	ProductRepository<Products> _productRepository;

	@Async
	public ValidateResponse checkUniqueName(String name) {
		Products p = (Products) findByQuery("from Products where name = :key").setParameter("key", name.trim())
				.uniqueResult();
		return new ValidateResponse(p == null, "name must be unique");
	}

	@Async
	public SearchResponse<ProductViewModel> findAll() {
		List<Products> list = _productRepository.findByQuery("from Products").list();
		List<ProductViewModel> productList = convertProductView(list);
		SearchResponse<ProductViewModel> data = new SearchResponse<ProductViewModel>();
		data.setItems(productList);
		data.setTotalRecords(productList.size());
		return data;
	}

	@Async
	public SearchResponse<ProductViewModel> Search(SearchRequest<Products> request) {
		if (request.getOrderBy().equals("CatgoryName")) {
			request.setOrderBy("Created");
		} else if (request.getOrderBy().equals("CreatedDate")) {
			request.setOrderBy("Created");
		}
		SearchResponse<Products> response = _productRepository.Paging(request, "name,content");

		SearchResponse<ProductViewModel> data = new SearchResponse<ProductViewModel>();
		data.setItems(convertProductView(response.getItems()));
		data.setTotalRecords(response.getTotalRecords());
		return data;
	}

	@Async
	public List<ProductViewModel> getFeatureProductById(int id) {
		List<Products> list = new ArrayList<Products>();
		if (id == 0) {
			list = _productRepository.findByQuery("from Products order by Created asc").setMaxResults(8).list();
		} else {
			list = _productRepository.findByQuery("from Products where categories.id = :id order by Created asc")
					.setParameter("id", id).setMaxResults(8).list();
		}
		return convertProductView(list);
	}

	@Async
	public List<ProductViewModel> getRelatedProductById(int id) {
		List<Products> list = new ArrayList<Products>();
		list = _productRepository.findByQuery("from Products where categories.id = :id order by Created asc")
				.setParameter("id", id).setMaxResults(8).list();
		return convertProductView(list);
	}

	@Async
	public List<ProductViewModel> getLastedProduct() {
		List<Products> list = new ArrayList<Products>();
		list = _productRepository.findByQuery("from Products order by Created desc").setMaxResults(3).list();
		return convertProductView(list);
	}

	@Async
	public List<ProductViewModel> getRandomProduct1() {
		List<Products> list = findByQuery("from Products order by id asc").setMaxResults(3).list();
		return convertProductView(list);
	}

	@Async
	public List<ProductViewModel> getRandomProduct2() {
		List<Products> list = findByQuery("from Products order by id desc").setMaxResults(3).list();
		return convertProductView(list);
	}

	@Async
	public SearchResponse<ProductViewModel> searchProductById(SearchRequest<Products> request) {
		SearchResponse<Products> res = new SearchResponse<Products>();
		if (request.getItemFilter().getId() == null&&request.getSearchText().equals("")) {
			res = _repository.Paging(request, "name");
		} else if (request.getItemFilter().getId()!=null&&request.getItemFilter().getId() == -1) {
			String countQuery = "select count(*) from Products where id in (select product.id from WishLists where user.id = :id)";
			String listQuery = "from Products where id in (select product.id from WishLists where user.id = :id) order by ";
			res = _repository.searchById(request, request.getUserId(), countQuery, listQuery);
		} else if (!request.getSearchText().equals("")) {
			long count = (long) _repository
					.findByQuery("select count(*) from Products where categories.name like :name")
					.setParameter("name", request.getSearchText()).uniqueResult();
			List<Products> list = _repository
					.findByQuery("from Products where categories.name like :name order by " + request.getOrderBy() + " "
							+ request.getDirection())
					.setParameter("name", request.getSearchText())
					.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize())
					.list();
			res.setItems(list);
			res.setTotalRecords(count);
		} else {
			String countQuery = "select count(*) from Products where categories.id = :id";
			String listQuery = "from Products where categories.id = :id order by ";
			res = _repository.searchById(request, request.getItemFilter().getId(), countQuery, listQuery);
		}
		SearchResponse<ProductViewModel> data = new SearchResponse<ProductViewModel>();
		data.setItems(convertProductView(res.getItems()));
		data.setTotalRecords(res.getTotalRecords());
		return data;
	}

	@Async
	private List<ProductViewModel> convertProductView(List<Products> list) {
		List<ProductViewModel> productList = new ArrayList<ProductViewModel>();
		for (Products item : list) {
			ProductViewModel p = new ProductViewModel();
			p.setAverageStar(item.getAverageStar());
			p.setCategoryId(item.getCategories().getId());
			p.setCatgoryName(item.getCategories().getName());
			p.setId(item.getId());
			p.setContent(item.getContent());
			p.setCreated(item.getCreated());
			p.setModifileDate(item.getModifileDate());
			p.setImages(item.getImages());
			p.setName(item.getName());
			p.setMoreImages(item.getMoreImages());
			p.setPrice(item.getPrice());
			p.setSalePrice(item.getSalePrice());
			p.setSlug(item.getSlug());
			p.setStar1(item.getStar1());
			p.setStar2(item.getStar2());
			p.setStar3(item.getStar3());
			p.setStar4(item.getStar4());
			p.setStar5(item.getStar5());
			p.setStatus(item.getStatus());
			p.setTopHot(item.getTopHot());
			p.setAverageStar(item.getAverageStar());
			productList.add(p);
		}
		return productList;
	}

	@Async
	public ProductViewModel getProductById(int id) {
		Products item = getById(id);
		ProductViewModel p = new ProductViewModel();
		p.setAverageStar(item.getAverageStar());
		p.setCategoryId(item.getCategories().getId());
		p.setId(item.getId());
		p.setContent(item.getContent());
		p.setCreated(item.getCreated());
		p.setImages(item.getImages());
		p.setName(item.getName());
		p.setMoreImages(item.getMoreImages());
		p.setPrice(item.getPrice());
		p.setSalePrice(item.getSalePrice());
		p.setSlug(item.getSlug());
		p.setStar1(item.getStar1());
		p.setStar2(item.getStar2());
		p.setStar3(item.getStar3());
		p.setStar4(item.getStar4());
		p.setStar5(item.getStar5());
		p.setStatus(item.getStatus());
		p.setTopHot(item.getTopHot());
		p.setAverageStar(item.getAverageStar());
		return p;
	}
}
