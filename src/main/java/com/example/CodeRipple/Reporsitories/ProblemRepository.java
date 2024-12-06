package com.example.CodeRipple.Reporsitories;

import com.example.CodeRipple.Entities.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {}
