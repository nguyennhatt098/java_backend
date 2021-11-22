package com.clientcontroller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;

@RestController()
@RequestMapping("/validate")
@CrossOrigin(origins = "*")
public class ValidateController {
	UserService _userService;
	public ValidateController() {
		_userService=new UserService(Users.class);
	}
	@GetMapping("/checkExistUserName")
	public String checkExistUserName(@RequestParam String userName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.getStatusUserName(userName)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/checkExistEmail")
	public String checkExistEmail(@RequestParam String email) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_userService.getStatusEmail(email)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
