package com.bakebuddy.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String email);

}
