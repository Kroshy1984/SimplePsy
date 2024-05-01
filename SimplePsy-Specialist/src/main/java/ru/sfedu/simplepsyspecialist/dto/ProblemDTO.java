package ru.sfedu.simplepsyspecialist.dto;

public class ProblemDTO {

    private String id;

    private String descriptionOfProblem;
    private String scoringId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescriptionOfProblem() {
        return descriptionOfProblem;
    }

    public void setDescriptionOfProblem(String description) {
        this.descriptionOfProblem = description;
    }

    public String getScoringId() {
        return scoringId;
    }

    public void setScoringId(String scoringId) {
        this.scoringId = scoringId;
    }
}