package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.entities.WishLists;
import com.repository.WishRepository;
import com.viewmodel.WishListViewModel;

public class WishService<T> extends ServiceBase<T> {
	WishRepository _wishRepository;
	public WishService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
		_wishRepository=new WishRepository(clazzToSet);
	}

	public List<WishListViewModel> getWishByUserId(int userId) {
		List<WishLists> list = findByQuery("from WishLists where user.id= :id").setParameter("id", userId)
				.list();
		List<WishListViewModel> data = new ArrayList<WishListViewModel>();
		for (WishLists item : list) {
			data.add(new WishListViewModel(item.getProduct().getId()));
		}
		return data;
	}
	public boolean deleteWish(WishLists w) {
		return _wishRepository.deleteWish(w);
		
	}
}
