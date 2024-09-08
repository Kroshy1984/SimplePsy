package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Transient;

import java.util.Map;

public class CompletedScoring {
    private String id;
    @Transient
    private String customerId; // добавьте поле customerId
    private Map<String, String> answers;
    private String title;
    public CompletedScoring(Map<String, String> answers, String title) {
        this.answers = answers;
        this.title = title;
    }

    public CompletedScoring() {
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

}