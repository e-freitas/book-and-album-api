package com.interview.model.book;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearch {
	
	private long timeBookApi;
	
	private BookItem[] items;

	public BookItem[] getItems() {
		return items;
	}

	public void setItems(BookItem[] items) {
		this.items = items;
	}

	public long getTimeBookApi() {
		return timeBookApi;
	}

	public void setTimeBookApi(long timeBookApi) {
		this.timeBookApi = timeBookApi;
	}

	@Override
	public String toString() {
		return "BookSearch [timeBookApi=" + timeBookApi + ", items=" + Arrays.toString(items) + "]";
	}
	
}
