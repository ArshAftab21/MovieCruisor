package com.stackroute.moviecruiser.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiser.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepoTest {
	
	@Autowired
	private transient MovieRepository movieRepository;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	private transient Movie movie;
	
	@Before
	public void setUp()
	{
		movie = new Movie("id1","Superman", "good movie", "www.abc.com", "2015-03-23",45.5, 40,"100");
		movie.setId(1);
	}
	
	/*@After 
	public void delete()
	{
		movieRepository.deleteAllInBatch();
	}*/
	
	@Test
	public void testSaveMovie() throws Exception {
		movieRepository.save(movie);
		final Movie movie = movieRepository.getOne(1);
		assertThat(movie.getTitle()).isEqualTo("Superman");
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		movieRepository.save(movie);
		final Movie movie = movieRepository.getOne(1);
		assertEquals("Superman", movie.getTitle());
		movie.setComments("Hi");
		movieRepository.save(movie);
		final Movie tempMovie = movieRepository.getOne(1);
		assertEquals("Hi", tempMovie.getComments());
	}
	
	@Test
	public void testGetMovie() throws Exception {
		movieRepository.save(movie);
		final Movie movie = movieRepository.getOne(1);
		assertEquals("Superman", movie.getTitle());
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		movieRepository.save(movie);
		final List<Movie> movies = movieRepository.findByUserId("100");
		assertEquals(movies.size(),1);
	}

	@Test
	public void testDeleteMovie() throws Exception {
		movieRepository.save(movie);
		final Movie movie = movieRepository.getOne(1);
		assertEquals("Superman", movie.getTitle());
		movieRepository.delete(movie);
		assertEquals(Optional.empty(), movieRepository.findById(1));
	}

}
