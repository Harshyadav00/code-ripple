package com.example.CodeRipple.Entities;

import jakarta.persistence.*;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String inputSample;
    private String outputSample;

    @Lob // For large inputs
    private String testCases; // JSON format: [{"input":"...","output":"..."}]

    // Getters and Setters
}
