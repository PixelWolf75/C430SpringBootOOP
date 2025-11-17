package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.Log

trait LogAnalysisService {

    def analyzeLog(log: Log);

    def summarizeLog(log: Log);

    def detectAnomalies(log: Log);

    def recommendFixes(log: Log);

    def devopsChat(prompt: String);

}

