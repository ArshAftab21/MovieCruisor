package com.stackroute.moviecruiser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	public final transient MovieRepository movieRepo;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		final Optional<Movie> object=movieRepo.findById(movie.getId());
		if(object.isPresent())
		{
			throw new MovieAlreadyExistsException("Could not save movie , movie already exits");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		final Movie updateMovie=movieRepo.findById(movie.getId()).orElse(null);
		if (updateMovie == null) {
			throw new MovieNotFoundException("Could not update. Movie not found");
		}
		updateMovie.setComments(movie.getComments());
		movieRepo.save(updateMovie);
		return updateMovie;

	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		final Movie movie=movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not delete. Movie not found");
		}
        movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found");
		}
		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepo.findByUserId(userId);
	}

}
