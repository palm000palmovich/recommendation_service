package com.project.command.controllers;

import com.project.command.model.User;
import com.project.command.model.UserDepositTransaction;
import com.project.command.model.UserWithdrawTransaction;
import com.project.command.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    //---------------FOR INTERACTIVE TEST---------

    //User with windraw transaction
    @GetMapping(path = "/withdraws/{id}")
    public ResponseEntity<UserWithdrawTransaction> getWithdrawOfUser(@PathVariable("id") String id){
        if (userService.getWihdraws(id) == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(userService.getWihdraws(id));
    }

    //User with deposit transaction
    @GetMapping(path = "/deposits/{id}")
    public ResponseEntity<UserDepositTransaction> getDepositOfUser(@PathVariable("id") String id){
        if (userService.getDeposits(id) == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(userService.getDeposits(id));
    }

}
