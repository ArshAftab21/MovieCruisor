package com.stackroute.movieauthenticationservice.service;

import com.stackroute.movieauthenticationservice.domain.User;
import com.stackroute.movieauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.movieauthenticationservice.exception.UserNotFoundException;

public interface UserService {
	boolean saveUser(User user) throws UserAlreadyExistsException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
