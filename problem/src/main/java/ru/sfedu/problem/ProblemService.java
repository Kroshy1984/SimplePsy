package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {


    private ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ru.sfedu.problem.ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem saveProblem(Problem problem) {
        return problemRepository.save(problem);
    }
}