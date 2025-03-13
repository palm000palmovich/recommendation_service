package com.project.command.component;


import com.project.command.model.DepositTransactions;
import com.project.command.model.WithdrawTransactions;
import com.project.command.repository.RecommendationsConstants;
import com.project.command.repository.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RecommendationRuleSet {
    @Autowired
    private RecommendationsRepository recommendationsRepository;
    private RecommendationsConstants recommendationsConstants;
    private Logger logger = LoggerFactory.getLogger(RecommendationRuleSet.class);

}
