package com.viewmodel;

public class SearchRequest<T> {
	private String direction;
	private String orderBy;
	private int pageIndex;
	private int pageSize;
	private String searchText;
	private int userId;
	private T itemFilter;
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public T getItemFilter() {
		return itemFilter;
	}
	public void setItemFilter(T itemFilter) {
		this.itemFilter = itemFilter;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
