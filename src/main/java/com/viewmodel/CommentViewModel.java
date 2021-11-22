package com.viewmodel;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentViewModel {
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
	    @Column(name = "UserName")
	    public String userName;
	    private String image;
	    private int userId;
	    private int rate;
	    public CommentViewModel() {
	    	
	    }
		public CommentViewModel(Integer id, String question, Date createdDate, Date modifyDate, boolean status,
				String userName, String image, int userId) {
			super();
			this.id = id;
			this.question = question;
			this.createdDate = createdDate;
			this.modifyDate = modifyDate;
			this.status = status;
			this.userName = userName;
			this.image = image;
			this.userId = userId;
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
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getRate() {
			return rate;
		}
		public void setRate(int rate) {
			this.rate = rate;
		}
	    
}
