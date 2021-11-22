/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "NotifiesUser")
@XmlRootElement
public class NotifiesUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "NotifiesId", referencedColumnName = "Id")
    @ManyToOne
    private NotifiesCustom notifiesId;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne
    private Users userId;
    @Column(name = "Status")
    public int status;
    public NotifiesUser() {
    }

    public NotifiesUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotifiesCustom getNotifiesId() {
        return notifiesId;
    }

    public void setNotifiesId(NotifiesCustom notifiesId) {
        this.notifiesId = notifiesId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
        if (!(object instanceof NotifiesUser)) {
            return false;
        }
        NotifiesUser other = (NotifiesUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotifiesUser[ id=" + id + " ]";
    }
    
}
