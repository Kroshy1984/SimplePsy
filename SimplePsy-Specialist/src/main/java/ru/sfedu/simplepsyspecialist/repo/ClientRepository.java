package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByContactEmail(String email);
}
