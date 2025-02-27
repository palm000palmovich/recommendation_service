
package com.project.command.services;

import com.project.command.exception.UserNotFoundException;
import com.project.command.model.RecommendationDTO;
import com.project.command.repository.RecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationsRepository recommendationsRepository;

    public RecommendationServiceImpl(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    public List<RecommendationDTO> getRecommendationByUserId(String user_id) {
        try {
            String userName = recommendationsRepository.findById(user_id).toString();
        } catch (UserNotFoundException e) {
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

}

