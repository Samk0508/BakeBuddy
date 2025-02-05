package com.bakebuudy.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BakeryOwner bakeryOwner;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false, unique = true)
    private String ifscCode;

    @Column(nullable = false)
    private String accountHolderName;
}
