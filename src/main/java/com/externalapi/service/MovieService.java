package com.externalapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestClientException;

import com.externalapi.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MovieService {
	
		List<Movie> getMovieDetails(String movieName) throws JsonMappingException, JsonProcessingException, RestClientException;

		Optional<Movie> getMovieById(Long id);
	
}
