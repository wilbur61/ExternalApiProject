package com.externalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.externalapi.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
}