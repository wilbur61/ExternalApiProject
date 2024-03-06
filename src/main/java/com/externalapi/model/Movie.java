package com.externalapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@JsonProperty("Title")
	String title;
	@JsonProperty("Year")
	String year;
	@JsonProperty("Rated")
	String rated;
	@JsonProperty("Released")
	String released;
	@JsonProperty("Genre")
	String genre;
	@JsonProperty("Director")
	String director;
	@JsonProperty("Writer")
	String writer;
	@JsonProperty("Actors")
	String actors;
	@JsonProperty("Awards")
	String awards;
	@JsonProperty("Metascore")
	String metascore;
	@JsonProperty("imdbRating")
	String imdbRating;
	@JsonProperty("Poster")
	String poster;
	@Lob
    @Column( length = Integer.MAX_VALUE, nullable = true)
	byte[] image;
	@Lob
	@Column( length = Integer.MAX_VALUE, nullable = true)
	String encodedImage;
}
