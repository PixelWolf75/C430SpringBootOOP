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
    public ResponseEntity<UUID> attachLog(@RequestBody Log log){
        //Return ID
        Log newLog = logDataService.createLog(log);
        return new ResponseEntity<>(newLog.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<Log> getLogs(@PathVariable UUID id){
        Log log = logDataService.getLog(id);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

    @PostMapping("/ai/analyze")
    public ResponseEntity<AIResponse> analyzeLogs(@RequestBody Log log){

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
