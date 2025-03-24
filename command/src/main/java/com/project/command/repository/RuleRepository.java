package com.project.command.repository;

import com.project.command.model.Rule;
import org.hibernate.annotations.DialectOverride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    //поиск правил для конкртеного продукта
    List<Rule> findByRecommendationProductId(UUID productId);
}
