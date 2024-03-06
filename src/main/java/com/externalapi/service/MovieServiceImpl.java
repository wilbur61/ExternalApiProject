package com.externalapi.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.externalapi.model.Movie;
import com.externalapi.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository repository;

	@Override
	public List<Movie> getMovieDetails(String movieName) throws JsonMappingException, JsonProcessingException, RestClientException {
		String uri= "https://www.omdbapi.com/?apikey=b79fdda2&t="+movieName;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Movie movie= mapper.readValue(restTemplate.getForObject(uri, String.class), Movie.class); 
		if(movie.getPoster() != null) {
		    try {
		    URL imageUrl = new URL(movie.getPoster());
		    movie.setImage(convertImageByte(imageUrl));
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		movie.setEncodedImage(Base64.encodeBase64String(movie.getImage()));
		repository.save(movie);
		List<Movie> movieList = repository.findAll();
		return movieList;
	}
	public static byte[] convertImageByte(URL url) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    InputStream is = null;
	    try {
	        is = new BufferedInputStream(url.openStream());
	        byte[] byteChunk = new byte[4096];
	        int n;
	        while ( (n = is.read(byteChunk)) > 0 ) {
	            baos.write(byteChunk, 0, n);
	        }
	        return baos.toByteArray();
	    }
	    catch (IOException e) {e.printStackTrace ();}
	    finally {
	        if (is != null) { is.close(); }
	    }
	    return null;
	}
	@Override
	public Optional<Movie> getMovieById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
}
