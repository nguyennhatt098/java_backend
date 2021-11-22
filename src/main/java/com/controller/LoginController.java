package com.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.TokenJwtUtil;
import com.entities.Authenticate;
import com.entities.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;

//import config.TokenJwtUtil;
@RestController()
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	private UserService _userService;

	public LoginController() {
		_userService = new UserService(Users.class);
	}

	@PostMapping("/login")
	public String login(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			u.setPassword(TokenJwtUtil.getMD5(u.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
//		} catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
			Users user = _userService.authenticate(u);
			if (user == null) {
				user = new Users();
				user.setMessage("You have entered the wrong account or password");
			} else if (user != null) {
				if (!user.getStatus()) {
					user.setMessage("Your account has been banned");
				} else {
					user.setMessage("true");
				}
			}
			if (user.getMessage() == "true") {
				String token = TokenJwtUtil.generateJwt(user.getId().toString());
				Authenticate auth = new Authenticate(token, user);
				String data = mapper.writeValueAsString(auth).toString();
				return data;
			}
			return mapper.writeValueAsString(new Authenticate(null, user)).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
