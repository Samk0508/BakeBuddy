package com.bakebuudy.entites;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bakery_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BakeryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bakeryName;

    @Column(nullable = false, unique = true)
    private String bakeryEmail;

    @Column(nullable = false, unique = true)
    private String bakeryMobile;

    @Column(nullable = false)
    private String bakeryAddress;

    private String logo;   // URL or file path of bakery logo

    private String banner; // URL or file path of bakery banner
    
    @OneToOne
    @JoinColumn(name = "bakery_owner_id", referencedColumnName = "id")
    private BakeryOwner bakeryOwner; // Link to BakeryOwner
}
