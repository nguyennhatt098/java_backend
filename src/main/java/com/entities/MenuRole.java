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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "MenuRole")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class MenuRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "ActionName")
    private String actionName;
    @JoinColumn(name = "MenuId", referencedColumnName = "Id")
    @ManyToOne
    private Menus menuId;
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    @ManyToOne
    private Role roleId;

    public MenuRole() {
    }

    public MenuRole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Menus getMenuId() {
        return menuId;
    }

    public void setMenuId(Menus menuId) {
        this.menuId = menuId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

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
        if (!(object instanceof MenuRole)) {
            return false;
        }
        MenuRole other = (MenuRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MenuRole[ id=" + id + " ]";
    }
    
}
