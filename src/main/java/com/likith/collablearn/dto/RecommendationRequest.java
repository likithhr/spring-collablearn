package com.likith.collablearn.dto;

import lombok.Data;

@Data
public class RecommendationRequest {
    private String subject;
    private String level;
    private double price;
}
