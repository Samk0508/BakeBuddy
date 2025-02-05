package com.bakebuudy.entites;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    
    private double totalSellingPrice;

    private int totalItem;
    
    private int totalMrpPrice;
    
    private int discount;

    private String couponCode;
    
    private int couponPrice;
}
