package com.project.command.services;

import com.project.command.exception.UserNotFoundException;
import com.project.command.model.RecommendationDTO;

import java.util.List;

public class RecommendationServiceImpl extends RecommendationService{
    public List<RecommendationDTO> getRecommendationByUserId(String user_id) {
        if (recommendationsRepository.findById(user_id).isEmpty()) {
            //logger
        }
        return recommendationsRepository.findById(user_id).orElseThrow(UserNotFoundException::new);
    }

}
