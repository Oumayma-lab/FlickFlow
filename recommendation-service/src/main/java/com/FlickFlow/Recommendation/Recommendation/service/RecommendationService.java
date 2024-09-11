package com.FlickFlow.Recommendation.Recommendation.service;

import com.Movies.Movies.dto.movieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import  com.Movies.Movies.service.movieService;
import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RestTemplate restTemplate;

    private final movieService movieService; // Assume MovieService contains fetchMovieDetails


    @Value("${flask.api.url}")
    private String flaskApiUrl;


    public RecommendationService(movieService movieService) {
        this.movieService = movieService;
    }


    public List<movieDto> getRecommendedMoviesForUser(String userId) {
        // Logic to get recommended movie IDs based on user preferences or history
        List<Integer> recommendedMovieIds = getRecommendedMovieIds(userId);

        // Fetch detailed movie information for each recommended movie
        return recommendedMovieIds.stream()
                .map(movieService::fetchMovieDetails)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private List<Integer> getRecommendedMovieIds(String userId) {
        // Placeholder for actual recommendation logic
        // This can be based on user history, ratings, etc.
        return List.of(1, 2, 3); // Example IDs
    }

    private movieDto convertToDto(MovieDetailsDTO details) {
        return new movieDto(
                details.getId(),
                details.getTitle(),
                details.getGenreIds(),
                details.getRating(),
                details.getReleaseDate(),
                details.getPosterPath(),
                details.getBackdropPath(),
                null
        );
    }

    public List<movieDto> getCollaborativeRecommendations(int userId, int numRecommendations) {
        String url = String.format("%s/recommend/collaborative?user_id=%d&num_recommendations=%d", flaskApiUrl, userId, numRecommendations);
        movieDto[] recommendations = restTemplate.getForObject(url, movieDto[].class);
        return Arrays.asList(recommendations);
    }

    public List<movieDto> getContentBasedRecommendations(String queryTitle, int numRecommendations) {
        String url = String.format("%s/recommend/content?query_title=%s&num_recommendations=%d", flaskApiUrl, queryTitle, numRecommendations);
        movieDto[] recommendations = restTemplate.getForObject(url, movieDto[].class);
        return Arrays.asList(recommendations);
    }

    public List<movieDto> getHybridRecommendations(int userId, int numRecommendations) {
        String url = String.format("%s/recommend/hybrid?user_id=%d&num=%d", flaskApiUrl, userId, numRecommendations);
        movieDto[] recommendations = restTemplate.getForObject(url, movieDto[].class);
        return Arrays.asList(recommendations);
    }
}
