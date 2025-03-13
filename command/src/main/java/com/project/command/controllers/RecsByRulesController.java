package com.project.command.controllers;


import com.project.command.model.RecommendationsByRules;
import com.project.command.services.RecsByRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recommendationsByRule")
public class RecsByRulesController {
    @Autowired
    private RecsByRulesService recsByRulesService;

    @PostMapping
    public ResponseEntity<RecommendationsByRules> saveNevRecommendation(@RequestBody RecommendationsByRules recommendationsByRules){
         return ResponseEntity.ok(recsByRulesService.saveNewRecommendation(recommendationsByRules));
    }

}
