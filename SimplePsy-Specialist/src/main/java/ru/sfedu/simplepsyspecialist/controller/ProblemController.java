package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Problem;
import ru.sfedu.simplepsyspecialist.entity.nested.ProblemStatus;
import ru.sfedu.simplepsyspecialist.service.ProblemService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/SimplePsy/V1/problem")
public class ProblemController {
    private ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewProblem (@RequestParam("problem") String descriptionOfProblem)
    {
        Problem problem = new Problem();
        LocalDateTime dateOfFirstContact = LocalDateTime.now();
        problem.setDescriptionOfProblem(descriptionOfProblem);
        problem.setStatus(ProblemStatus.NEW);
        problem.setDateOfFirstContact(dateOfFirstContact);
        String problemId = problemService.saveProblem(problem).getId();
        return new ResponseEntity<>(problemId, HttpStatus.OK);
    }
    @GetMapping("/customer/problems")
    public ResponseEntity<List<Problem>> getAllCustomersProblems(@RequestParam("problemsIds") List<String> problemsId)
    {
        System.out.println("In getAllCustomersProblems method the first problemId in list is: " + problemsId.get(0));
        List<Problem> problems = problemService.getAllCustomersProblems(problemsId);
        for (int i = 0; i < problems.size(); i++) {
            System.out.println("Adding problem " + problems.get(i).getId() + problems.get(i).getDescriptionOfProblem());
        }
        return ResponseEntity.ok(problems);
    }
    @PostMapping("/saveCustomersScoring")
    public ResponseEntity<String> saveCustomersScoring(@RequestParam("problemId") String problemId,
                                                       @RequestParam("scoringId") String scoringId)
    {
        System.out.println("In method saveCustomersScoring got the problemId: " + problemId
                + " \nscoringId: " + scoringId);
        problemService.saveCustomersScoring(problemId, scoringId);
        return ResponseEntity.ok("Problem with id " + problemId + " successfully saved scoring with id " + scoringId);
    }
//    @GetMapping("/getScoringAnswersByProblemId")
//    public ResponseEntity<List<String>> getScoringAnswersByProblemId(@RequestParam("problemId") String problemId)
//    {
//        System.out.println("In method getScoringAnswersByProblemId got the problemId: " + problemId );
//        List<String> answers = problemService.getScoringAnswers(problemId);
//        return ResponseEntity.ok(answers);
//    }

    @PostMapping("/cancel/{problemId}")
    public ResponseEntity<String> cancelScoring (@PathVariable String problemId) {
        problemService.cancelProblemById(problemId);
        return ResponseEntity.ok("Success");
    }
}
