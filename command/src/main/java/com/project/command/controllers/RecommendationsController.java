package com.project.command.controllers;

import com.project.command.model.DepositTransactions;
import com.project.command.model.RecommendationsDTO;
import com.project.command.model.User;
import com.project.command.model.WithdrawTransactions;
import com.project.command.services.RecommendationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/recommendation")
public class RecommendationsController {

    private Logger logger = LoggerFactory.getLogger(RecommendationsController.class);

    public RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService){
        this.recommendationsService = recommendationsService;
    }

    @GetMapping
    public List<User> getFewUsers(){
        return recommendationsService.getUsers();
    }

    @GetMapping(path = "/deposit/{userId}")
    public DepositTransactions getDepById(@PathVariable("userId") String id){
        return recommendationsService.getDepAmountById(id);
    }

    @GetMapping(path = "/withdraw/{userId}")
    public WithdrawTransactions getWithById(@PathVariable("userId") String id){
        return recommendationsService.getWithAmountById(id);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RecommendationsDTO>> getRecsForUser(@PathVariable("userId") String userId){
        List<RecommendationsDTO> recsList = recommendationsService.getRecsById(userId);
        logger.debug("List of Recommendations: " + recsList);

        if (recsList == null){return ResponseEntity.notFound().build();}

        return ResponseEntity.ok(recsList);
    }

}
