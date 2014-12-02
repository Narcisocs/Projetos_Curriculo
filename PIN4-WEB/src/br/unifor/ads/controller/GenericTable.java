package br.unifor.ads.controller;

import java.util.List;

public class GenericTable<T> {
	private List<T> items;
	private int currentPage;
	private int totalPages;
	private T newItem;
	private boolean itemDeleted;
	private T updatedItem;
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	private int pageSize;

	public T getNewItem() {
		return newItem;
	}
	public void setNewItem(T newItem) {
		this.newItem = newItem;
	}
	public boolean isItemDeleted() {
		return itemDeleted;
	}
	public void setItemDeleted(boolean itemDeleted) {
		this.itemDeleted = itemDeleted;
	}
	public T getUpdatedItem() {
		return updatedItem;
	}
	public void setUpdatedItem(T updatedItem) {
		this.updatedItem = updatedItem;
	}
}
