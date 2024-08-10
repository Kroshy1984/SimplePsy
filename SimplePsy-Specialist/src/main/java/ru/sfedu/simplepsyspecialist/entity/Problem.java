package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsyspecialist.entity.nested.ProblemStatus;

import java.time.LocalDateTime;

@Document(collection = "problems")
public class Problem {

    @Id
    private String id;
    private ProblemStatus status;

    private String descriptionOfProblem;
    private LocalDateTime dateOfFirstContact;
    private String scoringId;

    public Problem(ProblemStatus status, String descriptionOfProblem, LocalDateTime dateOfFirstContact) {
        this.status = status;
        this.descriptionOfProblem = descriptionOfProblem;
        this.dateOfFirstContact = dateOfFirstContact;
    }

    public Problem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
    }

    public String getDescriptionOfProblem() {
        return descriptionOfProblem;
    }

    public void setDescriptionOfProblem(String descriptionOfProblem) {
        this.descriptionOfProblem = descriptionOfProblem;
    }

    public LocalDateTime getDateOfFirstContact() {
        return dateOfFirstContact;
    }

    public void setDateOfFirstContact(LocalDateTime dateOfFirstContact) {
        this.dateOfFirstContact = dateOfFirstContact;
    }

    public String getScoringId() {
        return scoringId;
    }

    public void setScoringId(String scoringId) {
        this.scoringId = scoringId;
    }
}
