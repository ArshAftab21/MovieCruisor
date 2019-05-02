package com.stackroute.movieauthenticationservice.service;

import java.util.Map;

import com.stackroute.movieauthenticationservice.domain.User;

public interface SecurityTokenGenerator {
	
	Map<String,String> generateToken(User user);

}
