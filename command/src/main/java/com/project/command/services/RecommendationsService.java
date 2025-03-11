package com.project.command.services;

import com.project.command.model.Products;
import com.project.command.utils.RecommendationDeterminator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendationsService {
    private Logger logger = LoggerFactory.getLogger(RecommendationsService.class);
    @Autowired
    private RecommendationDeterminator recommendationDeterminator;
    public List<Products> getRecForUser(String id){
        logger.debug("Invoked method getRecForUser in RecommendationsService");
        List<Products> prodList = recommendationDeterminator.determineRecommendation(id);
        logger.debug("Received product: " + prodList);
        return prodList;
    }
}
