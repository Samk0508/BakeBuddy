package com.bakebuddy.entites;

import com.bakebuddy.enums.AccountStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bakery_owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BakeryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user; // Links to User entity

    @OneToOne(cascade = CascadeType.ALL)
    private BakeryDetails bakeryDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private BankDetails bankDetails;

    private boolean isApproved;
    
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

}
