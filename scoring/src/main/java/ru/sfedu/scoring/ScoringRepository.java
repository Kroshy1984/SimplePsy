package ru.sfedu.scoring;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoringRepository extends MongoRepository<Scoring, String> {
}
