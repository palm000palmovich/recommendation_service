package com.project.command.component;

import com.project.command.model.RecommendationDTO;
import com.project.command.model.Rule;
import com.project.command.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RecommendationRuleSetImplToCredit implements RecommendationRuleSet{

    @Override
    Optional<RecommendationDTO> matchesRules(String user_id){
        return Optional.of();
    }
}
