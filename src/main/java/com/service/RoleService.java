package com.service;

import java.util.List;

import com.entities.Actions;
import com.entities.Role;
import com.repository.RoleRepository;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;
import com.viewmodel.ValidateResponse;

public class RoleService extends ServiceBase<Role> {
	public RoleService(Class clazzToSet) {
		super(clazzToSet);
		_roleRepository = new RoleRepository(Role.class);
	}

	RoleRepository _roleRepository;

	public SearchResponse<Role> Search(SearchRequest<Role> request) {
		if (request.getOrderBy().equals("CreatedDate")) {
			request.setOrderBy("roleName");
		}
		SearchResponse<Role> response = _roleRepository.Paging(request, "roleName,description");
		return response;
	}

	public List<Actions> getActionByRole(int roleId) {
		return _roleRepository.getActionByRole(roleId);
	}

	public List<Actions> getActionByRoleAction(int roleId) {
		return _roleRepository.getActionByRoleAction(roleId);
	}
	public ValidateResponse checkUniqueRoleName(String name) {
		boolean status=findByQuery("from Role where roleName = :name").setParameter("name", name).uniqueResult()!=null;
		return new ValidateResponse(!status, "Name must be unique");
	}
}
