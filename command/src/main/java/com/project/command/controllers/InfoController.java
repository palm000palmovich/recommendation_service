package com.project.command.controllers;

import com.project.command.component.InfoServiceComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/management")
public class InfoController {
    private InfoServiceComponent infoServiceComponent;

    @Autowired
    public InfoController(InfoServiceComponent infoServiceComponent){
        this.infoServiceComponent = infoServiceComponent;
    }

    @GetMapping(path = "/info")
    public InfoServiceComponent getInfo(){
        return infoServiceComponent;
    }
}
