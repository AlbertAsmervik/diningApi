package com.example.diningApi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminReviewAction {

    // Does the admin accept the dining review?
    private boolean accepted;
}
