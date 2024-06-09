package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {
    Optional<List<Session>> findSessionsByDateBetweenAndSpecialistId(String start_date, String end_date, String specialist_id);
    Optional<List<Session>> findAllBySpecialistId(String id);
}
