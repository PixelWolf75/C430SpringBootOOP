package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.service.LogAnalysisService
import com.mthree.oopspringboot.api.IGeminiApi
import com.mthree.oopspringboot.entity.AIResponse
import com.mthree.oopspringboot.entity.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LogAnalysisServiceImpl extends LogAnalysisService {

    @Autowired
    val api: IGeminiApi = null

    def analyzeLog(log: Log): AIResponse = {
      val response = api.analyzeLogs(log.getContent)
      return new AIResponse(log, response)
    }

    def summarizeLog(log: Log): AIResponse = {
      val response = api.summarizeIncident(log.getContent)
      val aiResponse = new AIResponse(log, null)
      aiResponse.setSummary(response)
      return aiResponse
    }

    def detectAnomalies(log: Log): AIResponse = {
      val response = api.detectAnomaly(log.getContent)
      val aiResponse = new AIResponse(log, null)
      aiResponse.setAnomalies(response)
      return aiResponse
    }

    def recommendFixes(log: Log): AIResponse = {
      val response = api.generateRecommendFix(log.getContent)
      val aiResponse = new AIResponse(log, null)
      aiResponse.setRecommendation(response)
      return aiResponse
    }

    def devopsChat(prompt: String): AIResponse = {
      val response = api.getResponseFromDevopsChat(prompt)
      return new AIResponse(null, response)
    }
    
}
