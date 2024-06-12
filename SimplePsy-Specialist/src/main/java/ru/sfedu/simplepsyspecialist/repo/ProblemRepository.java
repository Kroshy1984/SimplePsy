package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sfedu.simplepsyspecialist.entity.Problem;

public interface ProblemRepository extends MongoRepository<Problem, String> {
}
