package com.example.CodeRipple.Services;

import com.example.CodeRipple.Entities.Submission;
import com.example.CodeRipple.Reporsitories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission saveSubmission(Submission submission){
        submission.setStatus("Pending");
        return (Submission) submissionRepository.save(submission);
    }

    public Optional<Submission> getSubmissionById(Long id){
        return submissionRepository.findById(id);
    }
}
