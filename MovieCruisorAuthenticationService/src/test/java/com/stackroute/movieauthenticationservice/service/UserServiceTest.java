package com.stackroute.movieauthenticationservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.movieauthenticationservice.domain.User;
import com.stackroute.movieauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.movieauthenticationservice.exception.UserNotFoundException;
import com.stackroute.movieauthenticationservice.repository.UserRepository;

public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	private User user;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("arshaftab", "arsh", "aftab", "12345678", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("can regsiter user", true, flag);
		verify(userRepository, times(1)).save(user);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException, UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
	}

	@Test
	public void testValidDataSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("arshaftab", userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());

	}

	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

}
