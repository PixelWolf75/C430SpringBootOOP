package com.mthree.oopspringboot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Log {


    /*
    *A UUID stands for Universally Unique Identifier, a 128-bit number used
    * to uniquely identify information or entities in computer systems across
    * the globe without requiring a central coordination authority.
     */

    @Id
    @GeneratedValue
    private UUID id;

    private String filename;

    private String content;

    //Enum
    private SourceType sourceType;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;


    //Defualt Const
    Log() {

    }

    //Parametrised Constructor
    Log(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(id, log.id) && Objects.equals(filename, log.filename) && Objects.equals(content, log.content) && sourceType == log.sourceType && Objects.equals(createdAt, log.createdAt) && Objects.equals(uploadedBy, log.uploadedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filename, content, sourceType, createdAt, uploadedBy);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", content='" + content + '\'' +
                ", sourceType=" + sourceType +
                ", createdAt=" + createdAt +
                ", uploadedBy=" + uploadedBy +
                '}';
    }
}
