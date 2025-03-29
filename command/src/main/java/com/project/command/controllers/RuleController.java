package com.project.command.controllers;

import com.project.command.model.Stats;
import com.project.command.services.RuleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rule")
public class RuleController {
    @Autowired
    public RuleService ruleService;

    @GetMapping(path = "/stats")
    public ResponseEntity<List<Stats>> getStatsOfAllRules(){
        List<Stats> statsList = ruleService.getStatsOfRules();
        if (statsList.size() == 0){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(statsList);
    }
}
