package com.clientcontroller;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.AnswerComments;
import com.entities.AnswerReviews;
import com.entities.Categories;
import com.entities.Comments;
import com.entities.Notifies;
import com.entities.NotifiesCustom;
import com.entities.Orders;
import com.entities.Products;
import com.entities.ReviewProducts;
import com.entities.WishLists;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AnswerService;
import com.service.CategoryService;
import com.service.CommentService;
import com.service.NotifiesCustomService;
import com.service.NotifiesService;
import com.service.OrdersService;
import com.service.ProductService;
import com.service.ReviewService;
import com.service.WishService;
import com.viewmodel.SearchRequest;

@RestController()
@RequestMapping("/home")
@CrossOrigin(origins = "*")
public class HomeController {
	ProductService _productService;
	CategoryService _categoryService;
	CommentService _commentService;
	WishService _wishService;
	AnswerService _answerService;
	NotifiesService _notifiesService;
	OrdersService _orderService;
	ReviewService _reviewService;
	NotifiesCustomService _notifiesCustomService;
	public HomeController() {
		_productService=new ProductService(Products.class);
		_categoryService=new CategoryService(Categories.class);
		_commentService=new CommentService(Comments.class);
		_wishService=new WishService(WishLists.class);
		_answerService=new AnswerService(AnswerComments.class);
		_notifiesService=new NotifiesService(Notifies.class);
		_orderService=new OrdersService(Orders.class);
		_reviewService=new ReviewService(ReviewProducts.class);
		_notifiesCustomService=new NotifiesCustomService(NotifiesCustom.class);
	}
	@GetMapping("/getFeatureProductById")
	public String getFeatureProductById(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String	data = mapper.writeValueAsString(_productService.getFeatureProductById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getRelatedProductById")
	public String getRelatedProductById(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.getRelatedProductById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getLastedProduct")
	public String getLastedProduct() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.getLastedProduct()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getRandomProduct1")
	public String getRandomProduct1() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.getRandomProduct1()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getRandomProduct2")
	public String getRandomProduct2() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.getRandomProduct2()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getFeatureCategoryById")
	public String getFeatureCategoryById() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_categoryService.getFeatureCategory()).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getAllCategories")
	public String getAllCategories() {
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
	@GetMapping("/searchProduct")
	public String searchProduct(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Products itemFilter = mapper.convertValue(allRequestParams,Products.class);
		SearchRequest<Products> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_productService.searchProductById(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getProductById")
	public String getProductById(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_productService.getProductById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getCommentByProductId")
	public String getCommentByProductId(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Comments itemFilter = mapper.convertValue(allRequestParams,Comments.class);
		SearchRequest<Comments> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			String	data = mapper.writeValueAsString(_commentService.searchCommentById(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getWishListByUserId")
	public String getWishListByUserId(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_wishService.getWishByUserId(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/searchAnswerCommentById")
	public String searchAnswerCommentById(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		AnswerComments itemFilter = mapper.convertValue(allRequestParams,AnswerComments.class);
		SearchRequest<AnswerComments> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			
			String	data = mapper.writeValueAsString(_answerService.searchById(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getNotifiesByUserId")
	public String getNotifiesByUserId(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_notifiesService.getListById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getOrderDetailsByCode")
	public String getOrderDetailsByCode(@RequestParam String code) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_orderService.getOrderDetailsByCode(code)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getReviewsByProductId")
	public String getReviewsByProductId(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ReviewProducts itemFilter = mapper.convertValue(allRequestParams,ReviewProducts.class);
		SearchRequest<ReviewProducts> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			
			String	data = mapper.writeValueAsString(_reviewService.searchById(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/searchAnswerReviewById")
	public String searchAnswerReviewById(@RequestParam Map<String,String> allRequestParams) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		AnswerComments itemFilter = mapper.convertValue(allRequestParams,AnswerComments.class);
		SearchRequest<AnswerComments> baseRequest=mapper.convertValue(allRequestParams,SearchRequest.class);
		baseRequest.setItemFilter(itemFilter);
		try {
			
			String	data = mapper.writeValueAsString(_reviewService.searchAnswerReviewById(baseRequest)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getCustomNotifiesByUserId")
	public String getCustomNotifiesByUserId(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_notifiesCustomService.getNotifiesCustomById(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getTotalNotifiesByUserId")
	public String getTotalNotifiesByUserId(@RequestParam int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			String	data = mapper.writeValueAsString(_notifiesService.getTotalNotifies(id)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
