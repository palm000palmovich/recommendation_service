package com.project.command.services;

import com.project.command.model.DepositTansactions;
import com.project.command.model.RecommendationsDTO;
import com.project.command.model.User;
import com.project.command.repository.RecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecommendationsService {
    @Autowired
    private RecommendationsRepository recommendationsRepository;

    public RecommendationsDTO getRecsForUser(String user_id){

        return null;
    }

    public List<User> getUsers(){
        return recommendationsRepository.getFewUsers();
    }

    public DepositTansactions getAmountById(UUID userId){
        return recommendationsRepository.getDepositAmountByUserId(userId);
    }

}
