package com.interview.rest;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.model.SearchResultAPI;
import com.interview.service.SearchServiceAPI;

@RestController
@RequestMapping("/search")
public class SearchRestAPI {
	
    private static final Logger log = LoggerFactory.getLogger(SearchRestAPI.class);
    
    @Autowired
    private SearchServiceAPI searchServiceAPI;
	
	@GetMapping
	ResponseEntity<SearchResultAPI> search(@PathParam(value = "term") String term) {
		try {
			SearchResultAPI searchResultAPI = searchServiceAPI.search(term);
			return ResponseEntity.ok(searchResultAPI);
		} catch(Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}