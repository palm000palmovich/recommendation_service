package com.project.command.services;

import com.project.command.model.User;
import com.project.command.repository.RecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private RecommendationsRepository recommendationsRepository;

    public List<User> get15Users(){
        return recommendationsRepository.getFewUsers();
    }
}
