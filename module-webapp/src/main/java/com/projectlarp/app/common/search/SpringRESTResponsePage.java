package com.projectlarp.app.common.search;

public class SpringRESTResponsePage {
	public int size;
	public long totalElements;
	public int totalPages;
	public int number;

	public SpringRESTResponsePage(int size, long totalElements, int totalPages, int number) {
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.number = number;
	}

}
