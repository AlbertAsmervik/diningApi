package com.example.diningApi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String displayName;

    private String city;
    private String state;
    private String zipcode;

    private Boolean interestedInPeanutAllergies;
    private Boolean interestedInEggAllergies;
    private Boolean interestedInDairyAllergies;
}

