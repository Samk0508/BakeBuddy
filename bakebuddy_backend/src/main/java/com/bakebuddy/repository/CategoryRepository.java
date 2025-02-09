package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

   

}
