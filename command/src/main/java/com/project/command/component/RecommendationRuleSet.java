package com.project.command.component;

import com.project.command.model.RecommendationDTO;

import java.util.Optional;

public interface RecommendationRuleSet {
    Optional<RecommendationDTO> matchesRules(String user_id);
}
