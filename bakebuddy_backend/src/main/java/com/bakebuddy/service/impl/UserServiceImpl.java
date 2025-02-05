package com.bakebuddy.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.config.JwtProvider;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.repository.UserRepository;
import com.bakebuddy.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	   @Autowired
	    private UserRepository userRepository;
	   @Autowired
		private JwtProvider jwtProvider;
	   @Autowired
		ModelMapper model;
	   
	   
	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		return user;
	}



	
	@Override
	public User findUserByEmail(String username) throws UserException {
		
		User user=userRepository.findByEmail(username);
		
		if(user!=null) {
			
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}


}
