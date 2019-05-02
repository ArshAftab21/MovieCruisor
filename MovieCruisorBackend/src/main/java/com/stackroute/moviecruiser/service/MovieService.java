package com.stackroute.moviecruiser.service;

import java.util.List;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;

public interface MovieService {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;

	Movie updateMovie(Movie movie) throws MovieNotFoundException;

	boolean deleteMovieById(int id) throws MovieNotFoundException;

	Movie getMovieById(int id) throws MovieNotFoundException;

	List<Movie> getMyMovies(String userId);



}
