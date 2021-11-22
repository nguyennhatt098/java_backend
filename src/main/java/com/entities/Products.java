package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Products")
@XmlRootElement

public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer id;
    @Column(name = "Name")
    public String Name;
    @Column(name = "Slug")
    private String slug;
    @Column(name = "Content")
    public String Content;
    @Column(name = "Images")
    private String images;
    @Column(name = "Price")
    public double Price;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Sale_Price")
    public Double SalePrice;
    @Column(name = "MoreImages")
    private String moreImages;
    @Column(name = "CreatedDate")
    public Date Created;
    @Column(name = "ModifileDate")
    public Date ModifileDate;
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
//    @JsonBackReference
    @JoinColumn(name = "Category_ID", referencedColumnName = "Id")
    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    private Categories categories;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String name, String slug, String content, String images, double price, boolean status, boolean topHot, float star1, float star2, float star3, float star4, float star5, float averageStar) {
        this.id = id;
        this.Name = name;
        this.slug = slug;
        this.Content = content;
        this.images = images;
        this.Price = price;
        this.status = status;
        this.topHot = topHot;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
        this.averageStar = averageStar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public Double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.SalePrice = salePrice;
    }

    public String getMoreImages() {
        return moreImages;
    }

    public void setMoreImages(String moreImages) {
        this.moreImages = moreImages;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        this.Created = created;
    }

    public Date getModifileDate() {
        return ModifileDate;
    }

    public void setModifileDate(Date modifileDate) {
        this.ModifileDate = modifileDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getTopHot() {
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categoryID) {
        this.categories = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Products[ id=" + id + " ]";
    }
    
}