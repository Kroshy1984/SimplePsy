package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ScoringRepository extends MongoRepository<Scoring, String> {
    @Query("{'answers': {'$size': 0}}")
    List<Scoring> findScoringsWithEmptyAnswers();

    @Query("{'answers': {'$size': 0}}")
    void deleteScoringsWithEmptyAnswers();
}
