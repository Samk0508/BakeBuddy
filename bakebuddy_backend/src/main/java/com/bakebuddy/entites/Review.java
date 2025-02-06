package com.bakebuddy.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double rating; // 1-5 stars
    
    private String reviewText;
    
    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
    
//    @ElementCollection
//    private List<String> productImages;

  
}
