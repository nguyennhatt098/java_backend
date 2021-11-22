package com.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.config.TokenJwtUtil;
import com.entities.Products;
import com.entities.Role;
import com.entities.Users;
import com.repository.UserRepository;
import com.viewmodel.ActionItem;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;
import com.viewmodel.ValidateResponse;

public class UserService<T> extends ServiceBase<T> {
	UserRepository _userRepository;

	public UserService(Class clazzToSet) {
		super(clazzToSet);
		_userRepository = new UserRepository(Users.class);
	}

	public Users authenticate(Users u) {
		return _userRepository.authenticate(u);
	}

	public SearchResponse<Users> Search(SearchRequest<Users> request) {
		if (request.getOrderBy().equals("RoleName")) {
			request.setOrderBy("createdDate");
		}
		String output = request.getOrderBy().substring(0, 1).toLowerCase() + request.getOrderBy().substring(1);
		request.setOrderBy(output);
		SearchResponse<Users> response = _userRepository.Paging(request, "userName,fullName,email,address");
		List<Users> list = new ArrayList<Users>();
		for (Users item : response.getItems()) {
			item.setRoleName(item.getRoleId().getRoleName());
			list.add(item);
		}
		response.setItems(list);
		return response;
	}

	public ActionItem<Users> createUser(Users u) {
		// TODO Auto-generated method stub
		try {
			u.setPassword(TokenJwtUtil.getMD5(u.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _userRepository.create(u);
	}

	public ActionItem<Users> updateUser(Users u) {
		// TODO Auto-generated method stub
		try {
			u.setPassword(TokenJwtUtil.getMD5(u.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _userRepository.update(u);
	}

	public ActionItem<Users> createUserFb(Users u) {
		Users user = (Users) findByQuery("from Users where email = :email").setParameter("email", u.getEmail())
				.uniqueResult();

		if (user != null) {
			if (!user.getStatus()) {
				return new ActionItem<Users>(user, "Your account has been banned");
			}
			return new ActionItem<Users>(user, "true");
		}
		try {
			u.setPassword(TokenJwtUtil.getMD5("123"));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		u.setPhone("");
		Role role = (Role) findByQuery("from Role where roleName= 'member'").uniqueResult();
		u.setRoleId(role);
		u.setUserName(u.getFullName().replaceAll("\\s+", ""));
		u.setAddress("");
		u.setStatus(true);
		return _userRepository.create(u);
	}

	public boolean getStatusUserName(String userName) {
		return findByQuery("from Users where userName = :userName").setParameter("userName", userName.trim())
				.uniqueResult() != null;
	}

	public boolean getStatusEmail(String email) {
		return findByQuery("from Users where email = :email").setParameter("email", email.trim())
				.uniqueResult() != null;
	}

	public ValidateResponse checkUniqueUserName(String userName) {
		boolean status = getStatusUserName(userName);
		return new ValidateResponse(!status, "User Name must be unique");
	}

	public ValidateResponse checkUniqueEmail(String email) {
		boolean status = getStatusEmail(email);
		return new ValidateResponse(!status, "Email must be unique");
	}

	public ActionItem<Users> createUserNomal(Users item) {
		// TODO Auto-generated method stub
		try {
			item.setPassword(TokenJwtUtil.getMD5(item.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Role role = (Role) findByQuery("from Role where roleName= 'member'").uniqueResult();
		item.setRoleId(role);
		item.setStatus(true);
		return _userRepository.create(item);
	}
}
