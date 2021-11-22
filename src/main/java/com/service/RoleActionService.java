package com.service;

import java.util.List;

import com.entities.RoleActions;
import com.repository.RoleActionRepository;
import com.viewmodel.ActionItem;
import com.viewmodel.ActionResponse;

public class RoleActionService<T> extends ServiceBase<T>{
	RoleActionRepository _roleActionRepository;
	public RoleActionService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
		_roleActionRepository=new RoleActionRepository<T>(clazzToSet);
	}
	public ActionItem<T> deleteByActionId(int entityId) {
	return _roleActionRepository.deleteByActionId(entityId);
	}
	public ActionResponse<T> deleteMutipleRoleAction(List<RoleActions> listEntity) {
		return _roleActionRepository.deleteMutipleRoleAction(listEntity);
	}
	public ActionResponse<T> createMutiple(List<T> list) {
		return _roleActionRepository.createMutiple(list);
	}
}
