package com.interview.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookItem {
	
	private BookInfo volumeInfo;

	public BookInfo getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(BookInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}

	@Override
	public String toString() {
		return "BookItem [volumeInfo=" + volumeInfo + "]";
	}
	
}
