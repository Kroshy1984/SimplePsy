package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.Map;

public class CompletedScoring {
    private String id;
    @Transient
    private String customerId; // добавьте поле customerId
    private Map<String, String> answers;
    private String title;
    private LocalDate date;

    public CompletedScoring(Map<String, String> answers, String title) {
        this.answers = answers;
        this.title = title;
        this.date = LocalDate.now();
    }

    public CompletedScoring() {
        this.date = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate localDate) {
        this.date = date;
    }
}