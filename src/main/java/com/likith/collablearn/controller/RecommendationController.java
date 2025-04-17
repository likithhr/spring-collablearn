package com.likith.collablearn.controller;

import com.likith.collablearn.dto.RecommendationRequest;
import com.likith.collablearn.dto.RecommendationResponse;
import com.likith.collablearn.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public List<RecommendationResponse> getRecommendations(@RequestBody RecommendationRequest request) {
        return recommendationService.getRecommendations(request);
    }
}
