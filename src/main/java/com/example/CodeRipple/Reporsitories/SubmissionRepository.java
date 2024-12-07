package com.example.CodeRipple.Reporsitories;

import com.example.CodeRipple.Entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findAllByProblemIdAndUserId(Long problemId, String userId) ;

    List<Submission> findAllByUserId(String userId);

    List<Submission> findAllByProblemId(Long problemId);
}
