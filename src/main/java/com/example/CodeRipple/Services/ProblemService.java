package com.example.CodeRipple.Services;

import com.example.CodeRipple.Entities.Problem;
import com.example.CodeRipple.Reporsitories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> getAllProblems() {
        return problemRepository.findAll() ;
    }
}
