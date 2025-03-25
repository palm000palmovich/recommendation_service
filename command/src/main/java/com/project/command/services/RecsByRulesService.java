package com.project.command.services;

import com.project.command.DTO.RecommendationsByRuleDTO;
import com.project.command.DTO.RuleDTO;
import com.project.command.component.RecommendationRuleSet;
import com.project.command.model.RecommendationsByRules;
import com.project.command.model.Rule;
import com.project.command.repository.RecommendationsByRulesRepository;
import com.project.command.repository.RecommendationsRepository;
import com.project.command.repository.RuleRepository;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecsByRulesService {
    private Logger logger = LoggerFactory.getLogger(RecsByRulesService.class);
    @Autowired
    private RecommendationsByRulesRepository recommendationsByRulesRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RecommendationRuleSet recommendationRuleSet;

    //get recommendation for user
    public List<RecommendationsByRules> selectRecommendation(String userId){
        return recommendationRuleSet.recommendationSelection(userId);
    }


    //Create
    public RecommendationsByRules saveRecByRule(RecommendationsByRuleDTO recDto){
        logger.debug("Received: " + recDto.toString() + "\n");

        RecommendationsByRules recommendation = new RecommendationsByRules();
        recommendation.setProduct_name(recDto.getProduct_name());
        recommendation.setProduct_text(recDto.getProduct_text());
        List<Rule> rules = new ArrayList<>();
        for (RuleDTO ruleDto : recDto.getRules()) {
            Rule rule = new Rule(ruleDto.getQuery(), ruleDto.getArgs(), ruleDto.isNegate(), recommendation);
            rules.add(rule);}

        //Sorting by query
        List<Rule> sortedRuleList = sortRules(rules);
        recommendation.setRule(sortedRuleList);


        if (rules.size() < 3 || rules.size() > 3){return null;}
        return recommendationsByRulesRepository.save(recommendation);
    }

    //All Recommendations
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

    //Delete recs and rules by productId
    @Transactional
    public void deleteRecommendationByProductId(UUID productId) {
        Optional<RecommendationsByRules> recommendationOpt = recommendationsByRulesRepository.findById(productId);

        if (recommendationOpt.isPresent()) {
            RecommendationsByRules recommendation = recommendationOpt.get();
            for (Rule rule : recommendation.getRule()) {
                ruleRepository.delete(rule);
            }
            recommendationsByRulesRepository.delete(recommendation);
        }
    }



    //Sorting rules by querys: USER_OF -> ACTIVE_USER_OF ->  TRANSACTION_SUM_COMPARE -> TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW
    private List<Rule> sortRules(List<Rule> ruleList) {
        List<String> order = Arrays.asList(
                "USER_OF",
                "ACTIVE_USER_OF",
                "TRANSACTION_SUM_COMPARE",
                "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW"
        );

        ruleList.sort(Comparator.comparingInt(rule -> order.indexOf(rule.getQuery())));

        return ruleList;
    }
}