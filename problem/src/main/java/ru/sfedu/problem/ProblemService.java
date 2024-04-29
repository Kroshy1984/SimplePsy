package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.problem.dto.ProblemDTO;
import ru.sfedu.problem.dto.ProblemMapper;

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

    public List<ProblemDTO> getAllCustomersProblems(List<String> problemsId) {
        List<Problem> problems = new ArrayList<>();

        for (int i = 0; i < problemsId.size(); i++) {
            problems.add(problemRepository.findById(problemsId.get(i)).get());
        }

        List<ProblemDTO> problemDTOList = new ArrayList<>();

        for (int i = 0; i < problems.size(); i++) {
            ProblemDTO problemDTO = ProblemMapper.INSTANCE.problemToProblemDTO(problems.get(i));
            problemDTOList.add(problemDTO);
            System.out.println("Adding problem " + problemDTO.getId() + problemDTO.getDescriptionOfProblem());
        }

        return problemDTOList;
    }
}