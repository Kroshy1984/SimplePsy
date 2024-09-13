package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsyspecialist.entity.nested.Question;
import ru.sfedu.simplepsyspecialist.entity.nested.TypeOfScoring;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Document("Scoring")
public class Scoring {
    private String id;
    private String title;
    private List<Question> questions;
    private TypeOfScoring type;
    private Date date;
//    @Transient
//    private static List<String> userData;
//    public List<String> answers = new ArrayList<>();
    @Transient
    public String customerId;

    public Scoring() {
    }

    public Scoring(List<Question> questions, String title) {
        this.questions = questions;
        this.title = title;
        this.date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public TypeOfScoring getType() {
        return type;
    }

    public void setType(TypeOfScoring type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}