package com.bakebuddy.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bakebuddy.domain.USER_ROLE;
import com.bakebuddy.model.User;
import com.bakebuddy.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "samkinspiron5518@gmail.com";

        if (userRepository.findByEmail(adminUsername)==null) {
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("Sam123"));
            adminUser.setFullName("Sam");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(USER_ROLE.ROLE_ADMIN);

            User admin=userRepository.save(adminUser);
        }
    }

}