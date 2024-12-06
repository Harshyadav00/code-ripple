package com.example.CodeRipple.Reporsitories;

import com.example.CodeRipple.Entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {}
