package com.project.command.controllers;


import com.project.command.model.RecommendationsByRules;
import com.project.command.services.RecsByRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/recommendations")
public class RecsByRulesController {
    @Autowired
    private RecsByRulesService recsByRulesService;

    @PostMapping
    public ResponseEntity<RecommendationsByRules> saveNevRecommendation(@RequestBody RecommendationsByRules recommendationsByRules){
         return ResponseEntity.ok(recsByRulesService.saveNewRecommendation(recommendationsByRules));
    }

    @GetMapping(path = "/clear-all")
    public ResponseEntity<Void> deleteAll(){
        recsByRulesService.clearDBs();
        return ResponseEntity.ok().build();
    }
}
