package com.interview.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.interview.model.SearchResultAPI;
import com.interview.model.Volume;
import com.interview.model.VolumeType;
import com.interview.model.album.AlbumInfo;
import com.interview.model.album.AlbumSearch;
import com.interview.model.book.BookInfo;
import com.interview.model.book.BookItem;
import com.interview.model.book.BookSearch;

@Component
public class SearchServiceAPI {
	
	private static final Logger log = LoggerFactory.getLogger(SearchServiceAPI.class);
	
    private final String bookApiBaseUrl = "https://www.googleapis.com/books/v1/volumes";
	
	private final String albumApiBaseUrl = "https://itunes.apple.com/search";
	
	@Value("${maxResults}")
	private int maxResults;
	
	private long timeBookApiTmp;
	private long timeAlbumApiTmp;
	
	public SearchResultAPI search(String term) {
		List<Volume> volumes = new ArrayList<Volume>();
		Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
            	BookSearch books = bookSearch(term);
            	timeBookApiTmp = books.getTimeBookApi();
            	if (books != null && books.getItems() != null)
        		for (BookItem i : books.getItems()) {
        			BookInfo vi = i.getVolumeInfo();
        			synchronized (volumes) {
        				volumes.add(new Volume(vi.getTitle(), vi.getAuthors() != null ? StringUtils.arrayToDelimitedString(vi.getAuthors(), ", ") : "", VolumeType.book.name()));
        			}
        		}
            }
        });
		Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
        		AlbumSearch albums = albumSearch(term);
        		timeAlbumApiTmp = albums.getTimeAlbumApi();
        		if (albums != null && albums.getResults() != null)
        		for (AlbumInfo ai : albums.getResults()) {
        			synchronized (volumes) {
        				volumes.add(new Volume(ai.getCollectionName(), ai.getArtistName(), VolumeType.album.name()));
        			}
        		}
            }
        });
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		volumes.sort(Comparator.comparing(Volume::getTitle));		
		SearchResultAPI searchResultAPI = new SearchResultAPI(volumes != null ? volumes.size() : 0, timeBookApiTmp, timeAlbumApiTmp, volumes);
		return searchResultAPI;
	}
	
	private BookSearch bookSearch(String term) {
		long start = System.currentTimeMillis();
		BookSearch bookSearch = new RestTemplate().getForObject(bookApiBaseUrl + "?q=" + term + "&maxResults=" + maxResults, BookSearch.class);
		long end  = System.currentTimeMillis();
		bookSearch.setTimeBookApi(end - start);
		return bookSearch;
	}
	
	private AlbumSearch albumSearch(String term) {
		long start = System.currentTimeMillis();
		AlbumSearch albumSearch = new RestTemplate(getMessageConverter()).getForObject(albumApiBaseUrl + "?term=" + term + "&limit=" + maxResults, AlbumSearch.class);
		long end  = System.currentTimeMillis();
		albumSearch.setTimeAlbumApi(end - start);
		return albumSearch;
	}
	
	private List<HttpMessageConverter<?>> getMessageConverter() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
		messageConverters.add(converter);  
		return messageConverters; 
	}

}
