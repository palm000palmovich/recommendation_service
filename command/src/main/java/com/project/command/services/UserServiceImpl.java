package com.project.command.services;

import com.project.command.model.User;
import com.project.command.repository.RecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private RecommendationsRepository recommendationsRepository;

    public List<User> getSomePeoples(){
        return recommendationsRepository.getFewUsers();
    }
}
