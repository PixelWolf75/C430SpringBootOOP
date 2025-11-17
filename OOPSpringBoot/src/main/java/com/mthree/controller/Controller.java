package com.mthree.controller;

import com.mthree.oopspringboot.entity.AIResponse;
import com.mthree.oopspringboot.entity.Log;
import com.mthree.oopspringboot.service.LogAnalysisService;
import com.mthree.oopspringboot.service.LogDataService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    LogAnalysisService logAnalysisService;

    @Autowired
    LogDataService logDataService;

    @PostMapping("/logs/upload")
    public ResponseEntity<UUID> attachLog(){
        //Return ID
        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<List<Log>> getLogs(@PathVariable long id){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/ai/analyze")
    public ResponseEntity<AIResponse> analyzeLogs(){
        return new ResponseEntity<>(new AIResponse(),HttpStatus.OK);
    }

    @PostMapping("/ai/summarize-incident")
    public ResponseEntity<AIResponse> summarize(){
        return new ResponseEntity<>(new AIResponse(),HttpStatus.OK);
    }

    @PostMapping("/ai/detect-anomaly")
    public ResponseEntity<AIResponse> detectAnomaly(){
        return new ResponseEntity<>(new AIResponse(), HttpStatus.OK);
    }

    @PostMapping("/ai/recommend-fix")
    public ResponseEntity<AIResponse> recommendFix(){
        return new ResponseEntity<>(new AIResponse(), HttpStatus.OK);
    }

    @PostMapping("/ai/devops-chat")
    public ResponseEntity<Void> devopsChat(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
