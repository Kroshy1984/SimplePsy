package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SimplePsyProblem/V1/problem")
public class ProblemController {
    private ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewProblem (@RequestBody Problem problem){
        problemService.saveProblem(problem);
        return ResponseEntity.ok("Problem successfully created");
    }
}
