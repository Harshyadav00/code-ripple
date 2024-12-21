package com.example.CodeRipple.Services;

import com.example.CodeRipple.Entities.Submission;
import com.example.CodeRipple.Reporsitories.SubmissionRepository;
import com.example.CodeRipple.Wrapper.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission saveSubmission(Submission submission){
        return (Submission) submissionRepository.save(submission);
    }

    public Optional<Submission> getSubmissionById(Long id){
        return submissionRepository.findById(id);
    }

    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByUserId(String userId) {
        List<Submission> submissions = submissionRepository.findAllByUserId(userId);
        if(!submissions.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, submissions, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Submission not found"));
    }

    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByProblemId(Long problemId) {

        List<Submission> submissions = submissionRepository.findAllByProblemId(problemId);
        if(!submissions.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, submissions, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Submission not found"));
    }

    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByProblemIdAndUserId(Long problemId, String userId) {
        List<Submission> submissions = submissionRepository.findAllByProblemIdAndUserId(problemId, userId);
        if(!submissions.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, submissions, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Submission not found"));
    }
}
