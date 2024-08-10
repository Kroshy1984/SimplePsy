package ru.sfedu.simplepsyspecialist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Problem;
import ru.sfedu.simplepsyspecialist.entity.nested.ProblemStatus;
import ru.sfedu.simplepsyspecialist.repo.ProblemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {


    private ProblemRepository problemRepository;
    ScoringService scoringService;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem saveProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public List<Problem> getAllCustomersProblems(List<String> problemsId) {
        List<Problem> problems = new ArrayList<>();

        for (int i = 0; i < problemsId.size(); i++) {
            Problem problem = problemRepository.findById(problemsId.get(i)).get();
            if (problem.getStatus() != ProblemStatus.DECLINED) {
                problems.add(problem);
            }
        }

        return problems;
    }

    public void saveCustomersScoring(String problemId, String scoringId) {
        Problem problem = problemRepository.findById(problemId).get();
        problem.setScoringId(scoringId);
        problemRepository.save(problem);
    }

//    public List<String> getScoringAnswers(String problemId) {
//        String scoringId = problemRepository.findById(problemId).get().getScoringId();
//        List<String> answers = scoringService.getScoringAnswers(scoringId);
//        System.out.println("Got the result in method getScoringAnswersByProblemId: " + answers);
//        return answers;
//    }

    public void cancelProblemById(String problemId) {
        Problem problemToUpdate = problemRepository.findById(problemId).orElseThrow();
        problemToUpdate.setStatus(ProblemStatus.DECLINED);
        problemRepository.save(problemToUpdate);
    }
}