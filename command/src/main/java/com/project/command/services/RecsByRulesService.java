package com.project.command.services;

import com.project.command.model.RecommendationsByRules;
import com.project.command.model.Rule;
import com.project.command.repository.RecommendationsByRulesRepository;
import com.project.command.repository.RecommendationsRepository;
import com.project.command.repository.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecsByRulesService {
    private Logger logger = LoggerFactory.getLogger(RecsByRulesService.class);

    @Autowired
    private RecommendationsByRulesRepository recommendationsByRulesRepository;

    @Autowired
    private RuleRepository ruleRepository;

    public RecommendationsByRules saveNewRecommendation(RecommendationsByRules recommendation){

        recommendationsByRulesRepository.save(recommendation);

        logger.debug("Рекоммендация: " + recommendation.getProduct_id() + " name " + recommendation.getProduct_name()
                + " text " + recommendation.getProduct_text());

        logger.debug("rule: {" + (recommendation.getRule()
                .stream()
                .map(rule -> "Query: " + rule.getQuery()
                        + "Arguments: " + rule.getArguments()
                        + " Is negate: " + rule.isNegate() + "}"))
                .collect(Collectors.joining(", ")));


        //Adding rules to DB from dinamic recommendation
        int ruleTableSizeBefore = ruleRepository.findAll().size();

        List<Rule> rulesList = recommendation.getRule();

        rulesList.forEach(rule -> ruleRepository.save(rule)); //Saving new rules

        int ruleTableSizeAfter = ruleRepository.findAll().size();


        logger.debug("Added new rules to rule-table: " +
                (ruleTableSizeBefore < ruleTableSizeAfter));

        return recommendation;
    }

    public List<RecommendationsByRules> getAllRecsByRule(){
        return recommendationsByRulesRepository.findAll();
    }

    public void clearDBs(){
        int rulesSize = ruleRepository.findAll().size();
        int recommendationsSize = recommendationsByRulesRepository.findAll().size();

        if ((rulesSize + recommendationsSize) != 0){
            ruleRepository.deleteAll();
            recommendationsByRulesRepository.deleteAll();
            logger.debug("DBs are clear: " + (ruleRepository.findAll().size() == 0 && recommendationsByRulesRepository.findAll().size() == 0));
        }

    }
}
