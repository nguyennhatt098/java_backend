package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Products;
import com.entities.Users;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import com.viewmodel.SearchRequest;

@RestController()
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	UserService _userService;
	public UserController() {
		_userService=new UserService(Users.class);
	}
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW USER', this)")
	public String search(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Users itemFilter = mapper.convertValue(allRequestParams,Users.class);
		SearchRequest<Users> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_userService.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD USER', this)")
	public String create(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.createUser(u)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT USER', this)")
	public String update(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.updateUser(u)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@DeleteMapping("/delete")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE USER', this)")
	public String delete(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.deleteById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/deleteMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE USER', this)")
	public String deleteMutiple(@RequestBody List<Users> list) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.deleteMutiple(list)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/checkUniqueUserName")
	public String checkUniqueUserName(String userName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_userService.checkUniqueUserName(userName)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/checkUniqueEmail")
	public String checkUniqueEmail(String email) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_userService.checkUniqueEmail(email)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
