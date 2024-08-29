package ru.sfedu.simplepsyspecialist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Scoring;
import ru.sfedu.simplepsyspecialist.repo.ScoringRepository;

import java.util.List;

@Service
public class ScoringService {
    ScoringRepository scoringRepository;
    ClientService clientService;
    CustomerService customerService;
    ProblemService problemService;

    @Autowired
    public ScoringService(ScoringRepository scoringRepository,
                          ClientService clientService,
                          CustomerService customerService,
                          ProblemService problemService) {
        this.scoringRepository = scoringRepository;
        this.clientService = clientService;
        this.customerService = customerService;
        this.problemService = problemService;
    }

    public Scoring save(Scoring scoring) {
//        Scoring scoring = scoringRepository.findById(scoringId).get();
//
//        System.out.println("Found the scoring with id: " + scoring.getId());
////        scoring.setAnswers(answers);
////        System.out.println("Ответы: " + scoring.getAnswers());
//        Scoring resultScoring = scoringRepository.save(scoring);
//        List<Scoring> scoringsWithEmptyAnswers = scoringRepository.findScoringsWithEmptyAnswers();
//        scoringRepository.deleteAll(scoringsWithEmptyAnswers);

        return scoringRepository.save(scoring);
    }

    public Scoring saveScoring(Scoring scoring)
    {
        return scoringRepository.save(scoring);
    }

    public void getScoringResult() {
    }

    public void saveCustomersScoring(String problemId, String scoringId) {
        problemService.saveCustomersScoring(problemId, scoringId);
    }

    public Scoring findById(String id) {
        return scoringRepository.findById(id).get();
    }

    public List<Scoring> findAll() {
        return scoringRepository.findAll();
    }
//    public List<String> getScoringAnswers(String scoringId) {
//        if (scoringRepository.findById(scoringId).isPresent()) {
//            return scoringRepository.findById(scoringId).get().getAnswers();
//        } else {
//            return null;
//        }
//    }
}
