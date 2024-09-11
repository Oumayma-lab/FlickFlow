package com.FlickFlow.Recommendation.Recommendation.controller;

import com.FlickFlow.Recommendation.Recommendation.service.RecommendationService;
import com.Movies.Movies.dto.movieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/collaborative")
    public List<movieDto> getCollaborativeRecommendations(
            @RequestParam int userId,
            @RequestParam(defaultValue = "10") int numRecommendations) {
        return recommendationService.getCollaborativeRecommendations(userId, numRecommendations);
    }

    @GetMapping("/content")
    public List<movieDto> getContentBasedRecommendations(
            @RequestParam String queryTitle,
            @RequestParam(defaultValue = "10") int numRecommendations) {
        return recommendationService.getContentBasedRecommendations(queryTitle, numRecommendations);
    }

    @GetMapping("/hybrid")
    public List<movieDto> getHybridRecommendations(
            @RequestParam int userId,
            @RequestParam(defaultValue = "10") int numRecommendations) {
        return recommendationService.getHybridRecommendations(userId, numRecommendations);
    }
    // Status Endpoint
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> status() {
        return ResponseEntity.ok(Map.of("status", "Service is running"));
    }

    // Update Endpoint (for incremental updates)
// <   @PostMapping("/update")
//    public ResponseEntity<Map<String, String>> update(@RequestBody Map<String, Object> data) {
//        try {
//            int userId = (int) data.get("userId");
//            int movieId = (int) data.get("movieId");
//            double rating = (double) data.get("rating");
//
//            recommendationService.updateUserItemMatrix(userId, movieId, rating);
//            recommendationService.retrainModel();
//
//            return ResponseEntity.ok(Map.of("message", "User-item matrix updated and models retrained successfully"));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }>
}
