package com.project.command.controllers;

import com.project.command.component.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/management")
public class InfoController {
    /*@Autowired
    private InfoService infoService;

    @GetMapping(path = "/info")
    public Map<String, String> getInfo(){
        Map<String, String> info = new HashMap<>();
        info.put("name", infoService.getName());
        info.put("version", infoService.getVersion());

        return info;
    }*/
}
