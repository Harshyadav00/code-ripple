package com.example.CodeRipple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;



    private Long problemId;
    private String userId; // Assuming unique user identifier
    private String code;
    private String language;
    private String status; // Pending, Accepted, Wrong Answer, etc.
    private String output;

    private LocalDateTime createdAt = LocalDateTime.now();


    public Submission() {
    }

    public Submission(Long id, Long problemId, String userId, String code, String language, String status, String output, LocalDateTime createdAt) {
        this.id = id;
        this.problemId = problemId;
        this.userId = userId;
        this.code = code;
        this.language = language;
        this.status = status;
        this.output = output;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Submission(Long problemId, String userId, String code, String language) {
        this.problemId = problemId;
        this.userId = userId;
        this.code = code;
        this.language = language;
    }
}
