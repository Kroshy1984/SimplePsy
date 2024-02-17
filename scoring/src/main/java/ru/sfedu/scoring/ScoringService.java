package ru.sfedu.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoringService {
    ScoringRepository scoringRepository;

    @Autowired
    public ScoringService(ScoringRepository scoringRepository) {
        this.scoringRepository = scoringRepository;
    }

    public Scoring save(List<String> answers) {
        Scoring scoring = new Scoring();
        scoring.setAnswers(answers);
        return scoringRepository.save(scoring);
    }
}
