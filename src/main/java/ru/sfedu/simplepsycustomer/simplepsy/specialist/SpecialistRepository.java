package ru.sfedu.simplepsycustomer.simplepsy.specialist;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SpecialistRepository extends MongoRepository<Specialist, String> {
}
