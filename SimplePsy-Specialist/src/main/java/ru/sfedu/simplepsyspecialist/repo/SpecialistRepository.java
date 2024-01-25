package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sfedu.simplepsyspecialist.entity.Specialist;

import java.util.Optional;

public interface SpecialistRepository extends MongoRepository<Specialist, String> {
    Optional<Specialist> findByEmail(String email);
}