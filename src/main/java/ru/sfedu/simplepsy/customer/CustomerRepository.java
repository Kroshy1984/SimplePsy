package ru.sfedu.simplepsy.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        @Query("{'name': '?0', $or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'contact.tg': '?1'}]}")
        List<Customer> findAllByNameAndSomeContact(String name, String contact);
}
