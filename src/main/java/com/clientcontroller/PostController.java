package com.clientcontroller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.TokenJwtUtil;
import com.entities.AnswerComments;
import com.entities.AnswerReviews;
import com.entities.Comments;
import com.entities.Notifies;
import com.entities.Orders;
import com.entities.ReviewProducts;
import com.entities.Users;
import com.entities.WishLists;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AnswerService;
import com.service.CommentService;
import com.service.NotifiesCustomService;
import com.service.NotifiesService;
import com.service.OrdersService;
import com.service.ReviewService;
import com.service.UserService;
import com.service.WishService;

@RestController()
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {
	UserService _userService;
	CommentService _commentService;
	WishService _wishService;
	OrdersService _orderService;
	AnswerService _answerService;
	ReviewService _reviewService;
	NotifiesService _notifiesService;

	public PostController() {
		_userService = new UserService(Users.class);
		_wishService = new WishService(WishLists.class);
		_commentService = new CommentService(Comments.class);
		_orderService = new OrdersService(Orders.class);
		_answerService = new AnswerService(AnswerComments.class);
		_reviewService = new ReviewService(ReviewProducts.class);
		_notifiesService=new NotifiesService(Notifies.class);
	}

	@PostMapping("/loginClient")
	public String loginClient(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			u.setPassword(TokenJwtUtil.getMD5(u.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Users user = _userService.authenticate(u);
		if (user == null) {
			user = new Users();
			user.setMessage("You have entered the wrong account or password");
		} else if (user != null) {
			if (!user.getStatus()) {
				user.setMessage("Your account has been banned");
			}else {
				user.setMessage("true");
			}
		}
		try {
			String data = mapper.writeValueAsString(user).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createComment")
	public String createComment(@RequestBody Comments c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_commentService.create(c)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping("/createWish")
	public String createWish(@RequestBody WishLists w) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_wishService.create(w)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/removeWish")
	public String removeWish(@RequestBody WishLists w) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_wishService.deleteWish(w)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Orders o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_orderService.createOrder(o)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createUser")
	public String createUser(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_userService.createUserFb(u)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createAnswerComment")
	public String createAnswerComment(@RequestBody AnswerComments a) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_answerService.create(a)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createReview")
	public String createReview(@RequestBody List<ReviewProducts> list) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_reviewService.createReview(list)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createAnswerReview")
	public String createReview(@RequestBody AnswerReviews item) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_reviewService.createAnswer(item)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/createUserNormal")
	public String createUserNormal(@RequestBody Users u) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_userService.createUserNomal(u)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/updateStatusNotify")
	public String updateStatusNotify(@RequestBody Notifies n) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(_notifiesService.UpdateNotifies(n)).toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
