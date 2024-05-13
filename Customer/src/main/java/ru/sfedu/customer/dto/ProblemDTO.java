package ru.sfedu.customer.dto;

public class ProblemDTO {

    private String id;
    private String descriptionOfProblem;
    private String dateOfFirstContact;

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

    public String getDateOfFirstContact() {
        return dateOfFirstContact;
    }

    public void setDateOfFirstContact(String dateOfFirstContact) {
        this.dateOfFirstContact = dateOfFirstContact;
    }
}
