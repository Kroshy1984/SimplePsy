package ru.sfedu.simplepsyspecialist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        @Query("{$or: [{'name': '?0'}, {$or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'contact.tg': '?1'}]}]}")
        List<Customer> findAllByNameAndSomeContact(String name, String contact);

        List<Customer> findAllByName(String name);

        @Query("{$or: [{'contact.phone': '?0'}, {'contact.email':'?0'}, {'contact.tg': '?0'}]}")
        List<Customer> findAllBySomeContact(String name);

        Optional<Customer> findByProblemsIdContaining(String problemId);
        Optional<Customer> findByContactEmail(String email);
        Optional<Customer> findByContactPhone(String phone);
        Optional<Customer> findByContactTg(String tg);
}
