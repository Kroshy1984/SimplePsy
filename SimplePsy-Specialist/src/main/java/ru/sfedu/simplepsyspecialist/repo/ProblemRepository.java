package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository extends MongoRepository<Problem, String> {
}
