package com.example.CodeRipple.Controller;

import com.example.CodeRipple.Entities.Submission;
import com.example.CodeRipple.Services.SubmissionService;
import com.example.CodeRipple.Wrapper.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<Submission> submitCode(@RequestBody Submission submission) {
        Submission savedSubmission = submissionService.saveSubmission(submission);
        // For now, just save and return. Add message queue handling later.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Submission>> getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id)
                .map(submission -> ResponseEntity.ok(new ApiResponse<>(true, submission, null)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, null, "Submission not found")));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByUserId(@PathVariable String userId){
        return submissionService.getSubmissionByUserId(userId);
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByProblemId(@PathVariable Long problemId){
        return submissionService.getSubmissionByProblemId(problemId);
    }

    @GetMapping("/{problemId}/{userId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByUserId(@PathVariable String userId, @PathVariable Long problemId){
        return submissionService.getSubmissionByProblemIdAndUserId(problemId, userId);
    }




}

