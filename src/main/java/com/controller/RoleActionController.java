package com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Products;
import com.entities.Role;
import com.entities.RoleActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.RoleActionService;
import com.service.RoleService;
@RestController()
@RequestMapping("/roleAction")
@CrossOrigin(origins = "*")
public class RoleActionController {
	RoleActionService _roleActionService;

	public  RoleActionController() {
		_roleActionService=new RoleActionService(RoleActions.class);
	}
	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD ROLE ACTIONS', this)")
	public String create(RoleActions roleAc) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_roleActionService.create(roleAc)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@DeleteMapping("/delete")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE ROLE ACTIONS', this)")
	public String delete(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_roleActionService.deleteByActionId(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/deleteMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE ROLE ACTIONS', this)")
	public String deleteMutiple(@RequestBody List<RoleActions> list) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_roleActionService.deleteMutipleRoleAction(list)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/createMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD ROLE ACTIONS', this)")
	public String createMutiple(@RequestBody List<RoleActions> list) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_roleActionService.createMutiple(list)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
