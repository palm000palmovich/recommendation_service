package com.project.command.controllers;


import com.project.command.model.DepositTansactions;
import com.project.command.model.User;
import com.project.command.services.RecommendationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/recommendations")
public class RecommendationsController {

    public RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService){
        this.recommendationsService = recommendationsService;
    }

    @GetMapping
    public List<User> getFewUsers(){
        return recommendationsService.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public DepositTansactions getById(@PathVariable("userId") UUID id){
        return recommendationsService.getAmountById(id);
    }

}
