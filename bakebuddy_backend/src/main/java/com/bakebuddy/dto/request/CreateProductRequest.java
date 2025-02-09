package com.bakebuddy.dto.request;

import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
	
    private String title;

    @Column(length = 2000)
    private String description;

    private int mrpPrice;

    private int sellingPrice;

    @Column(length = 5000)
    private List<String> images;

    private String category;
  
}
