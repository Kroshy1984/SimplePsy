package ru.sfedu.simplepsyspecialist.entity.nested;

import java.util.List;

public class Question {
    private String questionText;

    private List<String> options;

    public Question() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
