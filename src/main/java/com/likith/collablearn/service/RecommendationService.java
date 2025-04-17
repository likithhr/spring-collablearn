package com.likith.collablearn.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likith.collablearn.dto.RecommendationRequest;
import com.likith.collablearn.dto.RecommendationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FASTAPI_URL = "http://127.0.0.1:8000/recommend";

    public List<RecommendationResponse> getRecommendations(RecommendationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<String> entity;
        try {
            String jsonRequest = mapper.writeValueAsString(request);
            entity = new HttpEntity<>(jsonRequest, headers);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing request", e);
        }

        ResponseEntity<String> response = restTemplate.postForEntity(FASTAPI_URL, entity, String.class);
        List<RecommendationResponse> results = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode courses = root.get("recommended_courses");

            if (courses.isArray()) {
                for (JsonNode course : courses) {
                    String title = course.get("courseTitle").asText();
                    double price = course.get("price").asDouble();
                    int subscribers = course.get("numSubscribers").asInt();

                    results.add(new RecommendationResponse(title, price, subscribers));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing model response", e);
        }

        return results;
    }
}
