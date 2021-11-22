package com.controller;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Menus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.MenuService;
@RestController()
@RequestMapping("/menu")
@CrossOrigin(origins = "*")
public class MenuController {
	MenuService _menuService;
	 public MenuController() {
		// TODO Auto-generated constructor stub
		 _menuService=new MenuService(Menus.class);
	}
	@RequestMapping(value = "/getListByRoleId", produces = "text/plain;charset=UTF-8")
	public String getAll(int roleId) {
		 final ObjectMapper mapper = new ObjectMapper();
		String data;
		try {
			data = mapper.writeValueAsString(_menuService.GetListByRoleId(roleId)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
