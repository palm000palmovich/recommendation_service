package com.project.command.services;

import com.project.command.component.RecommendationRuleSet;
import com.project.command.model.DepositTransactions;
import com.project.command.model.RecommendationsDTO;
import com.project.command.model.User;
import com.project.command.model.WithdrawTransactions;
import com.project.command.repository.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecommendationsService {
    private Logger logger = LoggerFactory.getLogger(RecommendationsService.class);
    @Autowired
    private RecommendationsRepository recommendationsRepository;

    private RecommendationRuleSet recommendationRuleSet;

    @Autowired
    public RecommendationsService(RecommendationRuleSet recommendationRuleSet){this.recommendationRuleSet = recommendationRuleSet;}


    public List<User> getUsers(){
        return recommendationsRepository.getFewUsers();
    }

    public DepositTransactions getDepAmountById(String userId){

        logger.debug("Invoked getDepositById method from " + RecommendationsService.class);
        return recommendationsRepository.getDepositAmountByUserId(userId);
    }

    public WithdrawTransactions getWithAmountById(String userId){
        logger.debug("Invoked getWithdrawById method from " + RecommendationsService.class);

        return recommendationsRepository.getWithdrawAmountByUserId(userId);
    }

    public List<RecommendationsDTO> getRecsById(String userId){
        return recommendationRuleSet.getRecsForUserById(userId);
    }

}