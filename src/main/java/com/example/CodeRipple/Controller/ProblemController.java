package com.example.CodeRipple.Controller;

import com.example.CodeRipple.Entities.Problem;
import com.example.CodeRipple.Services.ProblemService;
import com.example.CodeRipple.Wrapper.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        Problem savedProblem = problemService.createProblem(problem) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProblem);
    }

    @GetMapping("/{problemId}")
    public  ResponseEntity<ApiResponse<Problem>> getProblemById(@PathVariable Long problemId){
        return problemService.getProblemById(problemId)
                .map(problem -> ResponseEntity.ok(new ApiResponse<>(true, problem, null)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, null, "Problem not found")));
    }

}
