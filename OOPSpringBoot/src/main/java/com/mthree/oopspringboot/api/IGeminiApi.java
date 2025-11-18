package com.mthree.oopspringboot.api;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IGeminiApi {
    String analyzeLogs(String text) throws JsonProcessingException;
    String summarizeIncident(String text);
    String detectAnomaly(String text) throws JsonProcessingException;
    String generateRecommendFix(String text);
    String getResponseFromDevopsChat(String text) throws JsonProcessingException;
}
