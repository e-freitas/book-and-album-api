package com.interview.model.album;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumSearch {
	
	private long timeAlbumApi;

	private AlbumInfo[] results;

	public long getTimeAlbumApi() {
		return timeAlbumApi;
	}

	public void setTimeAlbumApi(long timeAlbumApi) {
		this.timeAlbumApi = timeAlbumApi;
	}

	public AlbumInfo[] getResults() {
		return results;
	}

	public void setResults(AlbumInfo[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "AlbumSearch [timeAlbumApi=" + timeAlbumApi + ", results=" + Arrays.toString(results) + "]";
	}

}
