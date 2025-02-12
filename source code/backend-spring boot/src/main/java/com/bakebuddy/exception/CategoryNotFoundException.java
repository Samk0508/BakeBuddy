package com.bakebuddy.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String categoryNotFound) {
        super(categoryNotFound);
    }
}
