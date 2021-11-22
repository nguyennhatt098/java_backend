package com.viewmodel;
import java.util.List;

public class SearchResponse<T> {
	private long totalRecords;
	private List<T> items;
	private String message;
	private Boolean status;
	public SearchResponse() {
		
	}
	public SearchResponse(long totalRecords, List<T> items, String message, Boolean status) {
		super();
		this.totalRecords = totalRecords;
		this.items = items;
		this.message = message;
		this.status = status;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
