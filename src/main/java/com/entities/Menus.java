/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Menus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Menus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "Text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DisplayOrder")
    private int displayOrder;
    @Size(max = 2147483647)
    @Column(name = "Link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 100)
    @Column(name = "Class")
    private String class1;
    @Size(max = 100)
    @Column(name = "ActionName")
    private String actionName;
//    @OneToMany(mappedBy = "menuId")
//    private Collection<MenuRole> menuRoleCollection;

    public Menus() {
    }

    public Menus(Integer id) {
        this.id = id;
    }

    public Menus(Integer id, int displayOrder, boolean status) {
        this.id = id;
        this.displayOrder = displayOrder;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

//    @XmlTransient
//    public Collection<MenuRole> getMenuRoleCollection() {
//        return menuRoleCollection;
//    }
//
//    public void setMenuRoleCollection(Collection<MenuRole> menuRoleCollection) {
//        this.menuRoleCollection = menuRoleCollection;
//    }

    public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menus[ id=" + id + " ]";
    }
    
}
