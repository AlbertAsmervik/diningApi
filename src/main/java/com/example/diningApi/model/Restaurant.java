package com.example.diningApi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String zipCode;

    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;

    private Double overallScore;
}
