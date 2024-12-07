package com.example.CodeRipple.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String inputSample;
    private String outputSample;
    private String difficulty; // Easy, Medium, Hard

    @ElementCollection // For large inputs
    private List<TestCases> testCases; // JSON format: [{"input":"...","output":"..."}]

    public Problem(String title, String description, String inputSample, String outputSample, String difficulty, List<TestCases> testCases){
        this.title = title ;
        this.description = description ;
        this.inputSample = inputSample ;
        this.outputSample = outputSample ;
        this.difficulty = difficulty ;
        this.testCases = testCases ;
    }

    // Getters and Setters
}
