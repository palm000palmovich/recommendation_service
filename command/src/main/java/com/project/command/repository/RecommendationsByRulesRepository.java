package com.project.command.repository;

import com.project.command.model.RecommendationsByRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecommendationsByRulesRepository extends JpaRepository<RecommendationsByRules, UUID> {
}
