package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.config.Extension;
import com.entities.Notifies;
import com.entities.OrderDetails;
import com.entities.Orders;
import com.entities.Products;
import com.entities.ReviewProducts;
import com.entities.Role;
import com.viewmodel.ActionItem;
import com.viewmodel.ProductViewModel;
import com.viewmodel.SearchRequest;
import com.viewmodel.SearchResponse;

public class OrdersService extends ServiceBase<Orders> {

	public OrdersService(Class clazzToSet) {
		super(clazzToSet);
		// TODO Auto-generated constructor stub
	}

	public SearchResponse<Orders> Search(SearchRequest<Orders> request) {
		if (request.getOrderBy().equals("CreatedDate") || request.getOrderBy().equals("UserName")) {
			request.setOrderBy("created");
		} else if (request.getOrderBy().equals("StatusName")) {
			request.setOrderBy("status");
		} else {
			String output = request.getOrderBy().substring(0, 1).toLowerCase() + request.getOrderBy().substring(1);
			request.setOrderBy(output);
		}

		SearchResponse<Orders> response = _repository.Paging(request, "name,email,address");
		return response;
	}

	public ActionItem<Orders> createOrder(Orders o) {
		Session session = getCurrentSession();
		o.setStatus(0);
		try {
			session.getTransaction().begin();
			session.save(o);
			for (OrderDetails item : o.getOrderDetailsCollection()) {
				item.setOrderId(o);
				session.save(item);
			}

			session.getTransaction().commit();
			return new ActionItem<Orders>(null, "true");
		} catch (Exception e) {
			session.getTransaction().rollback();
			return new ActionItem<Orders>(null, e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public ActionItem<Orders> update(Orders o) {
		Session session = getCurrentSession();
		try {
			session.getTransaction().begin();

			if (o.getStatus() == 3) {
				o.setVerifyCode(Extension.generateRandomString());
				Notifies notifies = new Notifies();
				notifies.setContent("The order has been delivered successfully. You can rate the product");
				notifies.setLink(o.getVerifyCode());
				notifies.setImage(o.getOrderDetailsCollection().get(0).getImages());
				notifies.setStatus(1);
				notifies.setUser(o.getUserId());
				session.save(notifies);
			}
			session.update(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return new ActionItem<Orders>(o, e.getMessage());
		} finally {
			session.close();
		}
		return new ActionItem<Orders>(o, "true");
	}

	public List<OrderDetails> getOrderDetailsById(int id) {
		List<Object[]> obj = findBySqlQuery("select o.*,p.Name,p.Images,p.Content from OrderDetails o \r\n"
				+ "  join Products p \r\n" + "  on o.ProductId=p.Id where o.OrderId = :id").setParameter("id", id)
						.list();
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		for (Object[] orders : obj) {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setName(orders[5].toString());
			orderDetail.setPrice(Float.parseFloat(orders[3].toString()));
			orderDetail.setQuantity(Float.parseFloat(orders[4].toString()));
			orderDetail.setImages(orders[6].toString());
			orderDetail.setContent(orders[7].toString());
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}

	public List<ProductViewModel> getOrderDetailsByCode(String code) {

		List<OrderDetails> list = findByQuery("from OrderDetails where orderId.verifyCode = :code")
				.setParameter("code", code).list();
		return convertProductView(list);
	}

	private List<ProductViewModel> convertProductView(List<OrderDetails> list) {
		List<ProductViewModel> productList = new ArrayList<ProductViewModel>();
		for (OrderDetails item : list) {
			ProductViewModel p = new ProductViewModel();
			p.setAverageStar(item.getProductId().getAverageStar());
			p.setCategoryId(item.getProductId().getCategories().getId());
			p.setCatgoryName(item.getProductId().getCategories().getName());
			p.setId(item.getProductId().getId());
			p.setContent(item.getProductId().getContent());
			p.setCreated(item.getProductId().getCreated());
			p.setImages(item.getProductId().getImages());
			p.setName(item.getName());
			p.setMoreImages(item.getProductId().getMoreImages());
			p.setPrice(item.getPrice());
			p.setSalePrice(item.getProductId().getSalePrice());
			p.setSlug(item.getProductId().getSlug());
			p.setStar1(item.getProductId().getStar1());
			p.setStar2(item.getProductId().getStar2());
			p.setStar3(item.getProductId().getStar3());
			p.setStar4(item.getProductId().getStar4());
			p.setStar5(item.getProductId().getStar5());
			p.setStatus(item.getProductId().getStatus());
			p.setTopHot(item.getProductId().getTopHot());
			p.setName(item.getProductId().getName());
			p.setOrderId(item.getOrderId().getId());
			p.setAverageStar(item.getProductId().getAverageStar());
			productList.add(p);
		}
		return productList;
	}
}
