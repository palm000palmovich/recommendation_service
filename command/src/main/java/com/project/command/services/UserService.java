package com.project.command.services;

import com.project.command.model.User;
import com.project.command.model.UserDepositTransaction;
import com.project.command.model.UserWithdrawTransaction;
import com.project.command.repository.RecommendationsRepository;
import com.project.command.repository.UsersDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersDataRepository usersDataRepository;


    //-----------------FOR INTERACTIVE TEST--------------------------

    //User with windraw transaction
    public UserWithdrawTransaction getWihdraws(String id){
        return usersDataRepository.getUserWithdrawInfo(id);
    }

    //User with deposit transaction
    public UserDepositTransaction getDeposits(String id){
        return usersDataRepository.getUserDepositInfo(id);
    }
}
