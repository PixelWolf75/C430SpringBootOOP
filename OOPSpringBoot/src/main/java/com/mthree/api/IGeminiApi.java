package com.mthree.api;

public interface IGeminiApi {
    String analyzeLogs(String text);
    String summarizeIncident(String text);
    String detectAnomaly(String text);
    String generateRecommendFix(String text);
    String getResponseFromDevopsChat(String text);
}
