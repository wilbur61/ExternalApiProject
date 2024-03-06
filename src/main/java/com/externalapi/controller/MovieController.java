package com.externalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.externalapi.model.Movie;
import com.externalapi.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class MovieController {
	@Autowired
	MovieService service;

	@RequestMapping("/")
	String hello() {
		return "index";
	}

	@GetMapping("/getMovieDetails")
	public ResponseEntity<List<Movie>> saveProjectSubmission(@RequestParam String movieName)
			throws JsonMappingException, JsonProcessingException, RestClientException {
		return ResponseEntity.ok().body(service.getMovieDetails(movieName));

	}
	
	@GetMapping("/searchMovie")
	public String searchMovieTitle(Model model) {
		model.addAttribute("myMovie", new Movie());
		return "titleForm";
	}
	
	@PostMapping("/saveMovie")
    public String saveLoginSubmission(Model model, Movie myMovie) throws JsonMappingException, JsonProcessingException, RestClientException {
		List<Movie> movieInfo = service.getMovieDetails(myMovie.getTitle());
    	model.addAttribute("movieList", movieInfo);
        return "Title-Result";
    }

}
