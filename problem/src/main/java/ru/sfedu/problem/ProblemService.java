package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllCustomersProblems(List<String> problemsId) {
        List<String> problems = new ArrayList<>();
        for (int i = 0; i < problemsId.size(); i++) {
            problems.add(problemRepository.findById(problemsId.get(i)).get().getDescriptionOfProblem());
        }
        return problems;
    }
}