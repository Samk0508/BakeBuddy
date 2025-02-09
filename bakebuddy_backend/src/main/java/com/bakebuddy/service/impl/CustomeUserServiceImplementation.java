package com.bakebuddy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.User;
import com.bakebuddy.enums.UserRole;
import com.bakebuddy.repository.BakeryOwnerRepository;
import com.bakebuddy.repository.UserRepository;


@Service
public class CustomeUserServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;
    private final BakeryOwnerRepository bakeryOwnerRepository;
    private static final String BAKERY_OWNER_PREFIX = "bakeryowner_";

    public CustomeUserServiceImplementation(UserRepository userRepository, BakeryOwnerRepository bakeryOwnerRepository) {
        this.userRepository = userRepository;
        this.bakeryOwnerRepository = bakeryOwnerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.startsWith(BAKERY_OWNER_PREFIX)) {
            // Remove prefix to get the actual username/email
            String actualEmail = email.substring(BAKERY_OWNER_PREFIX.length());
            BakeryOwner bakeryOwner = bakeryOwnerRepository.findByUserEmail(actualEmail);
            if (bakeryOwner != null) {
            	return buildUserDetails(bakeryOwner.getUser().getEmail(), bakeryOwner.getUser().getPassword(), bakeryOwner.getUser().getRole());
            }
        } else {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }

        throw new UsernameNotFoundException("User or BakeryOwner not found with email - " + email);
    }

    private UserDetails buildUserDetails(String email, String password, UserRole role) {
        if (role == null) role = UserRole.ROLE_CUSTOMER;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }
}
