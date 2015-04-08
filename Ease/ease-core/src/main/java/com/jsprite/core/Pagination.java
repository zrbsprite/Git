package com.jsprite.core;

import java.util.List;

public class Pagination<T> {

	private int pageIndex = 0;
	
	private int pageSize = 10;
	
	private int pageCount;
	
	private int rowCount;
	
	private int itemStartIndex = 0;
	
	private int itemEndIndex = 0;
	
	private T model;
	
	private List<T> list;

	public Pagination(){
		
	}
	
	public Pagination(int pageIndex, int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public Pagination(int pageIndex, int pageSize, T model){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.model = model;
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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageCount() {
		this.pageCount = (rowCount-1)/pageSize + 1;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getItemStartIndex() {
		this.itemStartIndex = (pageIndex-1)*pageSize;
		return this.itemStartIndex;
	}

	public void setItemStartIndex(int itemStartIndex) {
		this.itemStartIndex = itemStartIndex;
	}

	public int getItemEndIndex() {
		this.itemEndIndex = getItemStartIndex()+pageSize-1; 
		return this.itemEndIndex;
	}

	public void setItemEndIndex(int itemEndIndex) {
		this.itemEndIndex = itemEndIndex;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}
}
