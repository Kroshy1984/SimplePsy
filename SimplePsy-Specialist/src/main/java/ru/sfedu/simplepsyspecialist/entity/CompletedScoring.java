package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Transient;

import java.util.Map;

public class CompletedScoring {
    @Transient
    private String customerId; // добавьте поле customerId
    private Map<String, String> answers;

    public CompletedScoring(Map<String, String> answers) {
        this.answers = answers;
    }

    public CompletedScoring() {
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