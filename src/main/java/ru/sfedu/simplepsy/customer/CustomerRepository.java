package ru.sfedu.simplepsy.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        @Query("{'name': '?0', $or: [{'contact.phone': '?1'}, {'contact.email':'?1'}, {'contact.tg': '?1'}] }")
        Optional<Customer> findByNameAndSomeContact(String name, String contact);

        @Query("{ $or: [ {'contact.phone': ?0}, {'contact.email': ?0}, {'contact.tg':  ?0}] }")
        Optional<Customer> findCustomerByContact(String contact);
        Optional<Customer> findCustomerByName(String name);;

>>>>>>> d1114152c2593ba7a0db7f974079d6789b2b5b3f
}
