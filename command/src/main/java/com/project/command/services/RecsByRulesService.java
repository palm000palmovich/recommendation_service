package com.project.command.services;

import com.project.command.model.RecommendationsByRules;
import com.project.command.repository.RecommendationsByRulesRepository;
import com.project.command.repository.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecsByRulesService {
    private Logger logger = LoggerFactory.getLogger(RecsByRulesService.class);

    @Autowired
    private RecommendationsByRulesRepository recommendationsByRulesRepository;

    public RecommendationsByRules saveNewRecommendation(RecommendationsByRules recommendation){
        logger.debug("Рекоммендация: " + recommendation.getProduct_id() + " name " + recommendation.getProduct_name()
                + " text " + recommendation.getProduct_text());
        return recommendationsByRulesRepository.save(recommendation);
    }

    public List<RecommendationsByRules> getAllRecsByRule(){
        return recommendationsByRulesRepository.findAll();
    }
}
