package com.example.CodeRipple.Controller;

import com.example.CodeRipple.Entities.Problem;
import com.example.CodeRipple.Services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService ;

    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        List<Problem> problems = problemService.getAllProblems() ;
        return ResponseEntity.ok(problems);
    }
}
