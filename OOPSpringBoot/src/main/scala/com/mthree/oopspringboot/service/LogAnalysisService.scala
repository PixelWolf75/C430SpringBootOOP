package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.AIResponse
import com.mthree.oopspringboot.entity.Log

trait LogAnalysisService {

    def analyzeLog(log: Log): AIResponse;

    def summarizeLog(log: Log): AIResponse;

    def detectAnomalies(log: Log): AIResponse;

    def recommendFixes(log: Log): AIResponse;

    def devopsChat(prompt: String): AIResponse;

}

