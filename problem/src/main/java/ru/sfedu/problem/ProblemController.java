package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/SimplePsyProblem/V1/problem")
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
        problem.setStatus(Status.NEW);
        problem.setDateOfFirstContact(dateOfFirstContact);
        String problemId = problemService.saveProblem(problem).getId();
        return new ResponseEntity<>(problemId, HttpStatus.OK);
    }
    @GetMapping("/customer/problems")
    public ResponseEntity<List<String>> getAllCustomersProblems(@RequestParam("problemsIds") List<String> problemsId)
    {
        System.out.println("In getAllCustomersProblems method the first problemId in list is: " + problemsId.get(0));
        List<String> problems = problemService.getAllCustomersProblems(problemsId);
        return ResponseEntity.ok(problems);
    }
}
