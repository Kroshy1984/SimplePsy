package ru.sfedu.notifications.telegram.model;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecRepository extends MongoRepository<Spec, String> {

    Spec findByUsername(String email);
}
