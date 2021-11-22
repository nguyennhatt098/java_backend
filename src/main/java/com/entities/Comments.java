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
@Table(name = "Comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "Question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ModifyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Products product;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users userId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentId")
//    private Collection<AnswerComments> answerCommentsCollection;

    public Comments() {
    }

    public Comments(Integer id) {
        this.id = id;
    }

    public Comments(Integer id, Date createdDate, Date modifyDate, boolean status) {
        this.id = id;
        this.createdDate = createdDate;
        this.modifyDate = modifyDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products productId) {
        this.product = productId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

//    @XmlTransient
//    public Collection<AnswerComments> getAnswerCommentsCollection() {
//        return answerCommentsCollection;
//    }
//
//    public void setAnswerCommentsCollection(Collection<AnswerComments> answerCommentsCollection) {
//        this.answerCommentsCollection = answerCommentsCollection;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comments[ id=" + id + " ]";
    }
    
}
