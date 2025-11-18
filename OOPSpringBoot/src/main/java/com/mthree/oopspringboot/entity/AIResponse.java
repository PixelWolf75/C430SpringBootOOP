package com.mthree.oopspringboot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class AIResponse {

    @Id
    @GeneratedValue
    private UUID id;

    // which log this AI analysis belongs to
    @ManyToOne
    @JoinColumn(name = "log_id")
    private Log log;

    //AI output (root cause, summary, fix, ...)
    private String analysis;

    private LocalDateTime createdAt;

    /*
    GENERATE INCIDENT SUMMARIES
    *Convert thousands of lines
    *log into a concise 5–10 line summary.
    *
     */
    private String summary;

    /*
    DETECT ANOMALIES
    * Send logs → AI identifies unusual patterns:
    * sudden spike in errors
    * memory leaks
    * slow DB queries
    * repeating warnings
     */

    private String anomalies;

    /*
    * RECOMMEND FIXES
    * DevOps engineer sends logs →
    * AI gives probable solutions.
     */
    private String recommendation;


    public AIResponse() {}

    public AIResponse(Log log, String analysis) {
        this.log = log;
        this.analysis = analysis;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(String anomalies) {
        this.anomalies = anomalies;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AIResponse that = (AIResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(log, that.log) && Objects.equals(analysis, that.analysis) && Objects.equals(createdAt, that.createdAt) && Objects.equals(summary, that.summary) && Objects.equals(anomalies, that.anomalies) && Objects.equals(recommendation, that.recommendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, log, analysis, createdAt, summary, anomalies, recommendation);
    }

    @Override
    public String toString() {
        return "AIResponse{" +
                "id=" + id +
                ", log=" + log +
                ", analysis='" + analysis + '\'' +
                ", createdAt=" + createdAt +
                ", summary='" + summary + '\'' +
                ", anomalies='" + anomalies + '\'' +
                ", recommendation='" + recommendation + '\'' +
                '}';
    }
}
