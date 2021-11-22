/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "RoleName")
    public String roleName;
    @Size(max = 2147483647)
    @Column(name = "Description")
    public String description;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
//    private Collection<RoleActions> roleActionsCollection;
//    @OneToMany(mappedBy = "roleId")
//    private Collection<MenuRole> menuRoleCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
//    private Collection<Users> usersCollection;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @XmlTransient
//    public Collection<RoleActions> getRoleActionsCollection() {
//        return roleActionsCollection;
//    }
//
//    public void setRoleActionsCollection(Collection<RoleActions> roleActionsCollection) {
//        this.roleActionsCollection = roleActionsCollection;
//    }

//    @XmlTransient
//    public Collection<MenuRole> getMenuRoleCollection() {
//        return menuRoleCollection;
//    }
//
//    public void setMenuRoleCollection(Collection<MenuRole> menuRoleCollection) {
//        this.menuRoleCollection = menuRoleCollection;
//    }

//    @XmlTransient
//    public Collection<Users> getUsersCollection() {
//        return usersCollection;
//    }
//
//    public void setUsersCollection(Collection<Users> usersCollection) {
//        this.usersCollection = usersCollection;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Roles[ id=" + id + " ]";
    }
    
}
