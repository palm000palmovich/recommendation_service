package com.project.command.repository;

import com.project.command.model.Stats;
import org.hibernate.internal.util.collections.StandardStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
    void deleteByRuleId(Long ruleId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE stats SET count = count + 1 WHERE rule_id = :ruleId", nativeQuery = true)
    void incrementCountByRuleId(@Param("ruleId") Long ruleId);

    @Query(value = "SELECT *  FROM stats", nativeQuery = true)
    List<Stats> getAllFromStats();
}
