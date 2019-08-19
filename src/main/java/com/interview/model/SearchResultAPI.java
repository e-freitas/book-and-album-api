package com.interview.model;

import java.util.List;

public class SearchResultAPI {
	
	private int numberOfResults;
	
	private long timeBookApi;
	
	private long timeAlbumApi;
	
	private final String timeUnit = "milliseconds";
	
	private List<Volume> volumes;
	
	public SearchResultAPI(int numberOfResults, long timeBookApi, long timeAlbumApi, List<Volume> volumes) {
		this.numberOfResults = numberOfResults;
		this.timeBookApi = timeBookApi;
		this.timeAlbumApi = timeAlbumApi;
		this.volumes = volumes;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public long getTimeBookApi() {
		return timeBookApi;
	}

	public void setTimeBookApi(long timeBookApi) {
		this.timeBookApi = timeBookApi;
	}

	public long getTimeAlbumApi() {
		return timeAlbumApi;
	}

	public void setTimeAlbumApi(long timeAlbumApi) {
		this.timeAlbumApi = timeAlbumApi;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	@Override
	public String toString() {
		return "SearchResultAPI [numberOfResults=" + numberOfResults + ", timeBookApi=" + timeBookApi + ", timeAlbumApi="
				+ timeAlbumApi + ", volumes=" + volumes + "]";
	}

}