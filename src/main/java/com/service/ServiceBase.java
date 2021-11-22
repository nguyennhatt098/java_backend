package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entities.Products;
import com.entities.Users;
import com.repository.HibernateUtil;
import com.repository.RepositoryBase;
import com.viewmodel.ActionItem;
import com.viewmodel.ActionResponse;
import com.viewmodel.FieldFilter;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public abstract class ServiceBase<T> {
	RepositoryBase<T> _repository;

	public ServiceBase(Class clazzToSet) {
		_repository = new RepositoryBase<T>((Class<T>) clazzToSet) {
		};
	}

	public Query findByQuery(String query) {
		return _repository.findByQuery(query);
	}
	
	public Query findBySqlQuery(String query) {
		return _repository.findBySqlQuery(query);
	}

	public ActionItem<T> deleteById(int entityId) {
		return _repository.deleteById(entityId);
	}

	public ActionItem<T> create(T entity) {
		return _repository.create(entity);
	}

	public ActionItem<T> update(T entity) {
		return _repository.update(entity);
	}

	public List<T> getAll() {
		return _repository.getAll();
	}
	public ActionResponse<T> updateMutiple(List<T> list) {
		return _repository.updateMutiple(list);
	}
	
	public ActionResponse<T> deleteMutiple(List<T> list) {
		return _repository.deleteMutiple(list);
	}
	protected Session getCurrentSession() {
		return _repository.getCurrentSession();
	}
	public T getById(int id) {
		return _repository.getById(id);
	}
	
}
