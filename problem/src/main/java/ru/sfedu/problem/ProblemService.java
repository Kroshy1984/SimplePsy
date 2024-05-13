package ru.sfedu.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
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

    public void saveCustomersScoring(String problemId, String scoringId) {
        Problem problem = problemRepository.findById(problemId).get();
        problem.setScoringId(scoringId);
        problemRepository.save(problem);
    }

    public List<String> getScoringAnswers(String problemId) {
        String scoringId = problemRepository.findById(problemId).get().getScoringId();
        String baseUrl = System.getenv().getOrDefault("SCORING_SERVICE_URL", "http://localhost:8084");
        String url = "/SimplePsyScoring/V1/scoring/getScoringAnswers";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<List<String>>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("scoringId", scoringId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        List<String> answers = response.block().getBody();

        System.out.println("Got the result in method getScoringAnswersByProblemId: " + answers);
        return answers;
    }
}