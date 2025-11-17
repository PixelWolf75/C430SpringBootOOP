package com.mthree.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @PostMapping("/logs/upload")
    public ResponseEntity<UUID> attachLog(){
        //Return ID
        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<List<UUID>> getLogs(@PathVariable long id){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/ai/analyze")
    public ResponseEntity<Void> analyzeLogs(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ai/summarize-incident")
    public ResponseEntity<Void> summarize(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ai/detect-anomaly")
    public ResponseEntity<Void> detectAnomaly(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ai/recommend-fix")
    public ResponseEntity<Void> recommendFix(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ai/devops-chat")
    public ResponseEntity<Void> devopsChat(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
