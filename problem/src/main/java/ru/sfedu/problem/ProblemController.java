package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/SimplePsyProblem/V1/problem")
public class ProblemController {
    private ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewProblem (@RequestParam("problem") String descriptionOfProblem,
                                                    @RequestParam("clientId") String clientId)
    {
        Problem problem = new Problem();
        LocalDateTime dateOfFirstContact = LocalDateTime.now();
        problem.setDescriptionOfProblem(descriptionOfProblem);
        problem.setStatus(Status.NEW);
        problem.setDateOfFirstContact(dateOfFirstContact);
        problem.setClientId(clientId);
        String problemId = problemService.saveProblem(problem).getId();
        return new ResponseEntity<>(problemId, HttpStatus.OK);
    }
}
