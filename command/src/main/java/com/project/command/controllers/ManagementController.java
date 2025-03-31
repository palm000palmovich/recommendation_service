package com.project.command.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.CacheEvict;

@RestController
@RequestMapping(path = "/management")
public class ManagementController {
    @PostMapping(path = "/clear-caches")
    @CacheEvict(cacheNames = {"depositAmount", "withdrawAmount"}, allEntries = true)
    public ResponseEntity<Void> clearCache(){
        return ResponseEntity.ok().build();
    }
}
