package com.viewmodel;

public class ActionItem<T> {
	 private T item;
	  private String  errorMessage;
	  
	public ActionItem(T item, String errorMessage) {
		super();
		this.item = item;
		this.errorMessage = errorMessage;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	  
}
