package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.exception.UserException;
import com.bakebuddy.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;


}
