package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sfedu.simplepsyspecialist.entity.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByContactEmail(String email);
}
