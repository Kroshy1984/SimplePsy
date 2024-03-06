package ru.sfedu.customer.dto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDTORepository extends MongoRepository<CustomerDTO, String> {
}
