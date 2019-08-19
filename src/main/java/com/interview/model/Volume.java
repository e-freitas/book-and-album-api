package com.interview.model;

public class Volume {
	
	public Volume(String title, String authors, String type) {
		this.title = title;
		this.authors = authors;
		this.type = type;
	}
	
	private String title;
	private String authors;
	private String type;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Volume [title=" + title + ", authors=" + authors + ", type=" + type + "]";
	}
}
