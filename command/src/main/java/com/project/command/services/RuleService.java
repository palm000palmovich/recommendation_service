package com.project.command.services;


import com.project.command.model.Stats;
import com.project.command.repository.StatsRepository;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleService {
    Logger logger = LoggerFactory.getLogger(RuleService.class);
    @Autowired
    public StatsRepository statsRepository;
    public List<Stats> getStatsOfRules(){
        logger.debug("Rule stats: " + statsRepository.getAllFromStats());
        return statsRepository.findAll();
    }
}
