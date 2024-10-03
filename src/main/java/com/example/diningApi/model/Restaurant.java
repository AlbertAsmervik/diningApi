package com.example.diningApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Double peanutScore;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Double eggScore;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Double dairyScore;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Double overallScore;
}
