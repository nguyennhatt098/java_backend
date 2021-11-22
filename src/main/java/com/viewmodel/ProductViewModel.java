package com.viewmodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductViewModel {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Id")
	    private Integer id;
	    @Column(name = "Name")
	    private String name;
	    @Column(name = "Slug")
	    private String slug;
	    @Column(name = "Content")
	    private String content;
	    @Column(name = "Images")
	    private String images;
	    @Column(name = "Price")
	    private double price;
	    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	    @Column(name = "Sale_Price")
	    private Double salePrice;
	    @Column(name = "MoreImages")
	    private String moreImages;
	    @Column(name = "Created")
	    private Date created;
	    @Column(name = "ModifileDate")
	    private Date modifileDate;
	    @Column(name = "Status")
	    private boolean status;
	    @Column(name = "TopHot")
	    private boolean topHot;
	    @Column(name = "Star1")
	    private float star1;
	    @Column(name = "Star2")
	    private float star2;
	    @Column(name = "Star3")
	    private float star3;
	    @Column(name = "Star4")
	    private float star4;
	    @Column(name = "Star5")
	    private float star5;
	    @Column(name = "AverageStar")
	    private float averageStar;
	    private String catgoryName;
	    private Integer categoryId;
	    private Integer orderId;
	    
	    public ProductViewModel() {}
		public ProductViewModel(Integer id, String name, String slug, String content, String images, double price,
				Double salePrice, String moreImages, Date created, Date modifileDate, boolean status, boolean topHot,
				float star1, float star2, float star3, float star4, float star5, float averageStar,
				String catgoryName) {
			super();
			this.id = id;
			this.name = name;
			this.slug = slug;
			this.content = content;
			this.images = images;
			this.price = price;
			this.salePrice = salePrice;
			this.moreImages = moreImages;
			this.created = created;
			this.modifileDate = modifileDate;
			this.status = status;
			this.topHot = topHot;
			this.star1 = star1;
			this.star2 = star2;
			this.star3 = star3;
			this.star4 = star4;
			this.star5 = star5;
			this.averageStar = averageStar;
			this.catgoryName = catgoryName;
		}
		
		public Integer getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Integer categoryId) {
			this.categoryId = categoryId;
		}
		public String getCatgoryName() {
			return catgoryName;
		}
		public void setCatgoryName(String catgoryName) {
			this.catgoryName = catgoryName;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSlug() {
			return slug;
		}
		public void setSlug(String slug) {
			this.slug = slug;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Double getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(Double salePrice) {
			this.salePrice = salePrice;
		}
		public String getMoreImages() {
			return moreImages;
		}
		public void setMoreImages(String moreImages) {
			this.moreImages = moreImages;
		}
		public Date getCreated() {
			return created;
		}
		public void setCreated(Date created) {
			this.created = created;
		}
		public Date getModifileDate() {
			return modifileDate;
		}
		public void setModifileDate(Date modifileDate) {
			this.modifileDate = modifileDate;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public boolean isTopHot() {
			return topHot;
		}
		public void setTopHot(boolean topHot) {
			this.topHot = topHot;
		}
		public float getStar1() {
			return star1;
		}
		public void setStar1(float star1) {
			this.star1 = star1;
		}
		public float getStar2() {
			return star2;
		}
		public void setStar2(float star2) {
			this.star2 = star2;
		}
		public float getStar3() {
			return star3;
		}
		public void setStar3(float star3) {
			this.star3 = star3;
		}
		public float getStar4() {
			return star4;
		}
		public void setStar4(float star4) {
			this.star4 = star4;
		}
		public float getStar5() {
			return star5;
		}
		public void setStar5(float star5) {
			this.star5 = star5;
		}
		public float getAverageStar() {
			return averageStar;
		}
		public void setAverageStar(float averageStar) {
			this.averageStar = averageStar;
		}
		public Integer getOrderId() {
			return orderId;
		}
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
	    
}
