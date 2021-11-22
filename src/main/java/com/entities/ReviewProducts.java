/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "ReviewProducts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class ReviewProducts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rate")
    private int rate;
    @Size(max = 2147483647)
    @Column(name = "Content")
    private String content;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 2147483647)
    @Column(name = "Image")
    private String image;
    @JoinColumn(name = "OrderId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Orders order;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Products product;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewId")
//    private Collection<AnswerReviews> answerReviewsCollection;

    public ReviewProducts() {
    }

    public ReviewProducts(Integer id) {
        this.id = id;
    }

    public ReviewProducts(Integer id, int rate, boolean status) {
        this.id = id;
        this.rate = rate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders orderId) {
        this.order = orderId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products productId) {
        this.product = productId;
    }

//    @XmlTransient
//    public Collection<AnswerReviews> getAnswerReviewsCollection() {
//        return answerReviewsCollection;
//    }
//
//    public void setAnswerReviewsCollection(Collection<AnswerReviews> answerReviewsCollection) {
//        this.answerReviewsCollection = answerReviewsCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewProducts)) {
            return false;
        }
        ReviewProducts other = (ReviewProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ReviewProducts[ id=" + id + " ]";
    }
    
}
