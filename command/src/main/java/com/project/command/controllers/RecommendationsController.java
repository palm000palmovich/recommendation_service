package com.project.command.controllers;

import com.project.command.model.Products;
import com.project.command.services.RecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recommendations")
public class RecommendationsController {
    private RecommendationsService recommendationsService;
    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService){
        this.recommendationsService = recommendationsService;
    }
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<List<Products>> getRecForUser(@PathVariable("user_id") String id){
        List<Products> prod = recommendationsService.getRecForUser(id);
        if (prod == null){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(prod);
    }
}
