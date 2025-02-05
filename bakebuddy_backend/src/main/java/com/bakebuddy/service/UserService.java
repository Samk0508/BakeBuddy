package com.bakebuddy.service;

import com.bakebuddy.entites.User;
import com.bakebuddy.exception.UserException;

public interface UserService {

	public User findUserProfileByJwt(String jwt)throws UserException;
	
	public User findUserByEmail(String email) throws UserException;

	  
}
