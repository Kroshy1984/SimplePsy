package ru.sfedu.scoring.scoring;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Scoring")
public class Scoring {
    private String id;
    private List<String> questions;
}
