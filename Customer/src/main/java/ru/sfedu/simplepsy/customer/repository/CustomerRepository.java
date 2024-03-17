package ru.sfedu.simplepsy.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.sfedu.simplepsy.customer.document.Contact;
import ru.sfedu.simplepsy.customer.document.Customer;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        @Query("{$or: [{'name': '?0'}, {$or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'contact.tg': '?1'}]}]}")
        List<Customer> findAllByNameOrSomeContact(String name, String contact);

        List<Customer> findAllByName(String name);

        @Query("{$or: [{'contact.phone': '?0'}, {'contact.email':'?0'}, {'contact.tg': '?0'}]}")
        List<Customer> findAllBySomeContact(String name);

}
