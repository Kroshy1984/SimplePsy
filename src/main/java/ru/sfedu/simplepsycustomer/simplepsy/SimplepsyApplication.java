package ru.sfedu.simplepsycustomer.simplepsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SimplepsyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimplepsyApplication.class, args);
    }
}