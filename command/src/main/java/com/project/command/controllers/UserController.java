package com.project.command.controllers;

import com.project.command.model.User;
import com.project.command.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){List<User> userList= userService.getSomePeoples();
        if (userList == null){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(userList);
    }
}
