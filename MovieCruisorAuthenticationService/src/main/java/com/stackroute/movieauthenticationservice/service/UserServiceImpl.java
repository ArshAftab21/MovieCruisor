package com.stackroute.movieauthenticationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieauthenticationservice.domain.User;
import com.stackroute.movieauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.movieauthenticationservice.exception.UserNotFoundException;
import com.stackroute.movieauthenticationservice.repository.UserRepository;

@Service  
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> u1 = userRepo.findById(user.getUserId());
		if (u1.isPresent()) {
			throw new UserAlreadyExistsException("user with id already exsts");
		}
		userRepo.save(user);
		return true;

	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("Sign in failed please check username or password");
		}

		return user;

	}

}
