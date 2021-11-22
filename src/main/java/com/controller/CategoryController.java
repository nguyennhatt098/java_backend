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
import com.entities.Products;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.CategoryService;
import com.viewmodel.SearchRequest;
@RestController()
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
	CategoryService _categoryService;
	public CategoryController() {
		_categoryService=new CategoryService(Categories.class);
	}
	@GetMapping("/getAll")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW CATEGORY', this)")
	public String getAll() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_categoryService.getAll()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW CATEGORY', this)")
	public String search(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Categories itemFilter = mapper.convertValue(allRequestParams,Categories.class);
		SearchRequest<Categories> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_categoryService.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD CATEGORY', this)")
	public String create(@RequestBody Categories c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_categoryService.create(c)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT CATEGORY', this)")
	public String update(@RequestBody Categories c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_categoryService.update(c)).toString();
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
			String	data = mapper.writeValueAsString(_categoryService.deleteById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/deleteMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE CATEGORY', this)")
	public String deleteMutiple(@RequestBody List<Categories> listP) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_categoryService.deleteMutiple(listP)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
