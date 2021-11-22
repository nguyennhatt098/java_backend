package com.viewmodel;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerCommentViewModel {
	 private String image;
	 public String fullName;
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
	    
		public AnswerCommentViewModel() {
			super();
		}
		public AnswerCommentViewModel(String image, String fullName, Integer id, String content, Date createdDate,
				int status) {
			super();
			this.image = image;
			this.fullName = fullName;
			this.id = id;
			this.content = content;
			this.createdDate = createdDate;
			this.status = status;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
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
	    
}
