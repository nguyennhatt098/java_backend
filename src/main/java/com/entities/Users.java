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
import javax.persistence.Transient;
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
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "UserName")
    public String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Password")
    private String password;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;
    @Column(name = "EditedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "FullName")
    public String fullName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Email")
    public String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Address")
    public String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 2147483647)
    @Column(name = "RessetPasswordCode")
    private String ressetPasswordCode;
    @Size(max = 2147483647)
    @Column(name = "Image")
    private String image;
    @Size(max = 2147483647)
    @Column(name = "Gender")
    private String gender;
    @Transient
    private String roleName;
    @Transient
    private String message;
    @Transient
    private int notifiesId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Collection<Orders> ordersCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Role roleId;
//    @OneToMany(mappedBy = "userId")
//    private Collection<AnswerComments> answerCommentsCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
//    private Collection<WishLists> wishListsCollection;
//    @OneToMany(mappedBy = "userId")
//    private Collection<AnswerReviews> answerReviewsCollection;
//    @OneToMany(mappedBy = "userId")
//    private Collection<Notifies> notifiesCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String userName, String password, String fullName, String phone, String email, String address, boolean status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public int getNotifiesId() {
		return notifiesId;
	}

	public void setNotifiesId(int notifiesId) {
		this.notifiesId = notifiesId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRessetPasswordCode() {
        return ressetPasswordCode;
    }

    public void setRessetPasswordCode(String ressetPasswordCode) {
        this.ressetPasswordCode = ressetPasswordCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    @XmlTransient
//    public Collection<Orders> getOrdersCollection() {
//        return ordersCollection;
//    }
//
//    public void setOrdersCollection(Collection<Orders> ordersCollection) {
//        this.ordersCollection = ordersCollection;
//    }
//
//    @XmlTransient
//    public Collection<Comments> getCommentsCollection() {
//        return commentsCollection;
//    }
//
//    public void setCommentsCollection(Collection<Comments> commentsCollection) {
//        this.commentsCollection = commentsCollection;
//    }


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

//    @XmlTransient
//    public Collection<AnswerComments> getAnswerCommentsCollection() {
//        return answerCommentsCollection;
//    }
//
//    public void setAnswerCommentsCollection(Collection<AnswerComments> answerCommentsCollection) {
//        this.answerCommentsCollection = answerCommentsCollection;
//    }

//    @XmlTransient
//    public Collection<WishLists> getWishListsCollection() {
//        return wishListsCollection;
//    }
//
//    public void setWishListsCollection(Collection<WishLists> wishListsCollection) {
//        this.wishListsCollection = wishListsCollection;
//    }

//    @XmlTransient
//    public Collection<AnswerReviews> getAnswerReviewsCollection() {
//        return answerReviewsCollection;
//    }
//
//    public void setAnswerReviewsCollection(Collection<AnswerReviews> answerReviewsCollection) {
//        this.answerReviewsCollection = answerReviewsCollection;
//    }

//    @XmlTransient
//    public Collection<Notifies> getNotifiesCollection() {
//        return notifiesCollection;
//    }
//
//    public void setNotifiesCollection(Collection<Notifies> notifiesCollection) {
//        this.notifiesCollection = notifiesCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ id=" + id + " ]";
    }
    
}
