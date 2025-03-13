package com.project.command.repository;

import com.project.command.model.RuleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<RuleDTO, Long> {
}
