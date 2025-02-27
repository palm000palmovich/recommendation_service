package com.project.command.controllers;

import com.project.command.model.RecommendationDTO;
import com.project.command.services.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @GetMapping("/recommendation/{user_id}")
    public ResponseEntity<Optional<RecommendationDTO>> getRecommendationByUserId(String user_id){
        Optional<RecommendationDTO> recommendations = Optional.ofNullable(recommendationService.getRecommendationByUserId(user_id));
        return ResponseEntity.ok(recommendations);
    }
}
