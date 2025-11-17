package com.mthree.oopspringboot.entity;

import javax.persistence.*;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AIResponse that = (AIResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(log, that.log) && Objects.equals(analysis, that.analysis) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, log, analysis, createdAt);
    }

    @Override
    public String toString() {
        return "AIResponse{" +
                "id=" + id +
                ", log=" + log +
                ", analysis='" + analysis + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
