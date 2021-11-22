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

import com.entities.Categories;
import com.entities.NotifiesCustom;
import com.entities.NotifiesUser;
import com.entities.Products;
import com.entities.Users;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.NotifiesCustomService;
import com.viewmodel.SearchRequest;

@RestController()
@RequestMapping("/notifiesCustom")
@CrossOrigin(origins = "*")
public class NotifiesCustomController {
	NotifiesCustomService _service;
	public NotifiesCustomController(){
		_service=new NotifiesCustomService(NotifiesCustom.class);
	}
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW PRODUCT', this)")
	public String search(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		NotifiesCustom itemFilter = mapper.convertValue(allRequestParams,NotifiesCustom.class);
		SearchRequest<NotifiesCustom> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_service.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD CATEGORY', this)")
	public String create(@RequestBody NotifiesCustom c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_service.create(c)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT CATEGORY', this)")
	public String update(@RequestBody NotifiesCustom c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.update(c)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@DeleteMapping("/delete")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String delete(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.deleteById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/deleteMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String deleteMutiple(@RequestBody List<NotifiesCustom> listP) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.deleteMutiple(listP)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/assignNotifies")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String assignNotifies(@RequestBody List<NotifiesUser> listP) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.addMutipleNotifies(listP)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/removeAssignNotifies")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String removeAssignNotifies(@RequestBody List<NotifiesUser> listP) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.DeleteMutipleNotifies(listP)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/updateMutipleNotifies")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String updateMutipleNotifies(@RequestBody List<NotifiesCustom> list) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_service.updateMutiple(list)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/searchNotExistUsers")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW PRODUCT', this)")
	public String searchNotExistUsers(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Users itemFilter = mapper.convertValue(allRequestParams,Users.class);
		SearchRequest<Users> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_service.PagingNotExistUser(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/searchExistUsers")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW PRODUCT', this)")
	public String searchExistUsers(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Users itemFilter = mapper.convertValue(allRequestParams,Users.class);
		SearchRequest<Users> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_service.PagingExistUser(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
