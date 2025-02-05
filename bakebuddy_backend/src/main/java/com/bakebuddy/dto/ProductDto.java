package com.bakebuddy.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductDto {

	 private Long id;

	    private String title;

	    private String description;

	    private int mrpPrice;

	    private int sellingPrice;

	    private int discountPercent;

	    private int quantity;

	    private List<String> images = new ArrayList<>();

	    private int numRatings;

	    private LocalDateTime createdAt;
}
