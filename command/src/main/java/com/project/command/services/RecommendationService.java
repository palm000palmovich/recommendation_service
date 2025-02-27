package com.project.command.services;

import com.project.command.model.RecommendationDTO;

import java.util.List;

public interface RecommendationService {
    List<RecommendationDTO> getRecommendationByUserId(String userId);
}
