package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.scheduling.annotation.Async;
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
import com.service.ProductService;
import com.viewmodel.ProductViewModel;
import com.viewmodel.SearchRequest;

@RestController()
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	ProductService _productService;

	public  ProductController() {
		_productService=new ProductService(Products.class);
	}
	@RequestMapping(value = "/getAll", produces = "text/plain;charset=UTF-8")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW PRODUCT', this)")
	public String getAll() {
		 final ObjectMapper mapper = new ObjectMapper();
		String data;
		try {
			data = mapper.writeValueAsString(_productService.findAll()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW PRODUCT', this)")
	public String search(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Products itemFilter = mapper.convertValue(allRequestParams,Products.class);
		SearchRequest<Products> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_productService.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/create")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'ADD PRODUCT', this)")
	public String create(@RequestBody Products p) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_productService.create(p)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/deleteMutiple")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE PRODUCT', this)")
	public String deleteMutiple(@RequestBody List<Products> listP) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_productService.deleteMutiple(listP)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT PRODUCT', this)")
	public String update(@RequestBody Products p) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_productService.update(p)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@DeleteMapping("/delete")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'DELETE PRODUCT', this)")
	public String delete(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_productService.deleteById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/checkUniqueName")
	public String checkUniqueName(String params) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.checkUniqueName(params)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

