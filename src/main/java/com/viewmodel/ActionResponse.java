package com.viewmodel;

import java.util.List;

public class ActionResponse<T> {
	private boolean status;
	 private List<ActionItem<T>>   successItems;
	 private List<ActionItem<T>>  failureItems;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ActionItem<T>> getSuccessItems() {
		return successItems;
	}
	public void setSuccessItems(List<ActionItem<T>> successItems) {
		this.successItems = successItems;
	}
	public List<ActionItem<T>> getFailureItems() {
		return failureItems;
	}
	public void setFailureItems(List<ActionItem<T>> failureItems) {
		this.failureItems = failureItems;
	}
	 
}
