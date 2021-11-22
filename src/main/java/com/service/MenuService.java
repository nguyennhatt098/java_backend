package com.service;

import java.util.List;

import com.entities.Menus;
import com.repository.MenuRepository;

public class MenuService extends ServiceBase<Menus>{
	MenuRepository _repository;
	public MenuService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
		_repository=new MenuRepository(clazzToSet);
	}
	public List<Menus> GetListByRoleId(int roleId){
		return _repository.GetListByRoleId(roleId);
	}
}
