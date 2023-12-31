package ru.sfedu.simplepsy.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

        @Query("{'name': '?0', $or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'tg': '?1'}] }")
        Optional<Customer> findByNameAndSomeContact(String name, String contact);



}
