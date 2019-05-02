package com.stackroute.moviecruiser.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mvc;

	@MockBean
	private transient MovieService service;

	private transient Movie movie;

	@InjectMocks
	private MovieController controller;

	static List<Movie> movies;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		movies = new ArrayList<>();
		movie = new Movie("id1","POC", "good movie", "www.abc.com", "2015-03-23",45.5, 40,"arshaftab");
		movie.setId(1);
		movies.add(movie);
		movie = new Movie("id2","POC-2", "very good movie", "www.cde.com", "2015-09-23",45.5, 40,"arshaftab");
		movie.setId(2);
		movies.add(movie);
	}

	@Test
	public void testSaveNewMovieSuccess() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcnNoYWZ0YWIiLCJpYXQiOjE1NTU5MjIyMjN9.YoaC3HwVI8yLGskS7BnEzyzgU_sDp2qGqejJo_BPoKI";
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movie").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isCreated());
		verify(service, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testUpdateMovieSuccess() throws Exception {
		movie.setComments("not so good movie");
		when(service.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/movie/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isOk());
		verify(service, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/movie/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetMovieById() throws Exception {
		when(service.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/movie/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).getMovieById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetMyMovies() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcnNoYWZ0YWIiLCJpYXQiOjE1NTU5MjIyMjN9.YoaC3HwVI8yLGskS7BnEzyzgU_sDp2qGqejJo_BPoKI";
		when(service.getMyMovies("arshaftab")).thenReturn(movies);
		mvc.perform(get("/api/movie/").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(service, times(1)).getMyMovies(Mockito.anyString());
		verifyNoMoreInteractions(service);
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
