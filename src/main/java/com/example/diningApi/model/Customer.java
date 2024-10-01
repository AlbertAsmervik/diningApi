package com.example.diningApi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data  // Lombok will generate getters, setters, toString, hashCode, and equals
@NoArgsConstructor  // Generates a default constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}
