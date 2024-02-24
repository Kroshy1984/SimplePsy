package ru.sfedu.session;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {
    Optional<List<Session>> findSessionsByDateBetweenAndClientId(String start_date, String end_date, String client_id);
    Optional<List<Session>> findAllByClientId(String id);
}
