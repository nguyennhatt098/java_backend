package com.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Actions;
import com.entities.Products;
import com.entities.Role;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ProductService;
import com.service.RoleService;
import com.viewmodel.SearchRequest;

@RestController()
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {
	RoleService _roleService;

	public RoleController() {
		_roleService = new RoleService(Role.class);
	}
	@RequestMapping(value = "/getAll", produces = "text/plain;charset=UTF-8")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW ROLE', this)")
	public String getAll() {
		 final ObjectMapper mapper = new ObjectMapper();
		String data;
		try {
			data = mapper.writeValueAsString(_roleService.getAll()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW ROLE', this)")
	public String search(@RequestParam Map<String, String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Role itemFilter = mapper.convertValue(allRequestParams, Role.class);
		SearchRequest<Role> baseRequest = mapper.convertValue(allRequestParams, SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String data = mapper.writeValueAsString(_roleService.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'CREATE ROLE', this)")
	public String create(@RequestBody Role role) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_roleService.create(role)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT ROLE', this)")
	public String update(@RequestBody Role role) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_roleService.update(role)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getActionByRole")
	public String getActionByRole(@RequestParam int roleId) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			String data = mapper.writeValueAsString(_roleService.getActionByRole(roleId)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getActionByRoleAction")
	public String getActionByRoleAction(@RequestParam int roleId) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			String data = mapper.writeValueAsString(_roleService.getActionByRoleAction(roleId)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/checkUniqueRoleName")
	public String checkUniqueRoleName(String name) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_roleService.checkUniqueRoleName(name)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
