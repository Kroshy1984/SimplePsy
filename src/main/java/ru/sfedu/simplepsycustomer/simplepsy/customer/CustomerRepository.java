package ru.sfedu.simplepsycustomer.simplepsy.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        @Query("{$or: [{'name': '?0'}, {$or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'contact.tg': '?1'}]}]}")
        List<Customer> findAllByNameAndSomeContact(String name, String contact);

//        @Query("{ $or: [ {'contact.phone': ?0}, {'contact.email': ?0}, {'contact.tg':  ?0}] }")
//        Optional<Customer> findCustomerByContact(String contact);
//        Optional<Customer> findCustomerByName(String name);
}