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
@Table(name = "Categories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Name")
    public String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Slug")
    public String slug;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "ModifileDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifileDate;
    @Column(name = "Images")
    private String images;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryID")
//    private Collection<Products> productsCollection;

    public Categories() {
    }

    public Categories(Integer id) {
        this.id = id;
    }

    public Categories(Integer id, String name, String slug, boolean status) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.status = status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifileDate() {
        return modifileDate;
    }

    public void setModifileDate(Date modifileDate) {
        this.modifileDate = modifileDate;
    }

//    @XmlTransient
//    public Collection<Products> getProductsCollection() {
//        return productsCollection;
//    }
//
//    public void setProductsCollection(Collection<Products> productsCollection) {
//        this.productsCollection = productsCollection;
//    }

    public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categories[ id=" + id + " ]";
    }
    
}
