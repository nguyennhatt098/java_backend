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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "NotifiesCustom")
@XmlRootElement
public class NotifiesCustom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer id;
    @Size(max = 2147483647)
    @Column(name = "Content")
    public String content;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "ModifyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 2147483647)
    @Column(name = "Image")
    private String image;
    @Size(max = 2147483647)
    @Column(name = "Link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;
    @OneToMany(mappedBy = "notifiesId")
    private Collection<NotifiesUser> notifiesUserCollection;
    @Transient
    private int notifiesId;
    public NotifiesCustom() {
    }

    public NotifiesCustom(Integer id) {
        this.id = id;
    }

    public NotifiesCustom(Integer id, int status) {
        this.id = id;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNotifiesId() {
		return notifiesId;
	}

	public void setNotifiesId(int notifiesId) {
		this.notifiesId = notifiesId;
	}

	@XmlTransient
    public Collection<NotifiesUser> getNotifiesUserCollection() {
        return notifiesUserCollection;
    }

    public void setNotifiesUserCollection(Collection<NotifiesUser> notifiesUserCollection) {
        this.notifiesUserCollection = notifiesUserCollection;
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
        if (!(object instanceof NotifiesCustom)) {
            return false;
        }
        NotifiesCustom other = (NotifiesCustom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotifiesCustom[ id=" + id + " ]";
    }
    
}
