package com.project.command.controllers;


import com.project.command.DTO.RecommendationsByRuleDTO;
import com.project.command.component.RecommendationRuleSet;
import com.project.command.model.RecommendationsByRules;
import com.project.command.model.Rule;
import com.project.command.services.RecsByRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/recommendations")
public class RecsByRulesController {
    @Autowired
    private RecsByRulesService recsByRulesService;

    @PostMapping
    public ResponseEntity<RecommendationsByRules> saveRecs(
            @RequestBody RecommendationsByRuleDTO recDto){

        return ResponseEntity.ok(recsByRulesService.saveRecByRule(recDto));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<RecommendationsByRules>> getRecForUser(@PathVariable("userId") String userId){
        List<RecommendationsByRules> recsList = recsByRulesService.selectRecommendation(userId);

        if (recsList == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(recsList);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteRecommendationByRule(@PathVariable("productId") UUID productId){
        recsByRulesService.deleteRecommendationByProductId(productId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/clear-all")
    public ResponseEntity<Void> deleteAll(){
        recsByRulesService.clearDBs();
        return ResponseEntity.ok().build();
    }
}