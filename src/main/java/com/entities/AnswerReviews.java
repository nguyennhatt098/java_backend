/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "AnswerReviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class AnswerReviews implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "Content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;
    @JoinColumn(name = "ReviewId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private ReviewProducts reviewProduct;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne
    private Users user;

    public AnswerReviews() {
    }

    public AnswerReviews(Integer id) {
        this.id = id;
    }

    public AnswerReviews(Integer id, Date createdDate, int status) {
        this.id = id;
        this.createdDate = createdDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ReviewProducts getReviewProduct() {
        return reviewProduct;
    }

    public void setReviewProduct(ReviewProducts reviewId) {
        this.reviewProduct = reviewId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users userId) {
        this.user = userId;
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
        if (!(object instanceof AnswerReviews)) {
            return false;
        }
        AnswerReviews other = (AnswerReviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AnswerReviews[ id=" + id + " ]";
    }
    
}
