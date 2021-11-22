package com.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Orders;
import com.entities.Products;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.OrdersService;
import com.viewmodel.SearchRequest;
@RestController()
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrdersController {
	OrdersService _orderService;
	public OrdersController() {
		_orderService=new OrdersService(Orders.class);
	}
	@GetMapping("/search")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'VIEW ORDERS', this)")
	public String search(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Orders itemFilter = mapper.convertValue(allRequestParams,Orders.class);
		SearchRequest<Orders> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_orderService.Search(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/update")
	@PreAuthorize("@appAuthorizer.authorize(authentication, 'EDIT ORDERS', this)")
	public String update(@RequestBody Orders o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_orderService.update(o)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getOrderDetailById")
	public String getOrderDetailById(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_orderService.getOrderDetailsById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
