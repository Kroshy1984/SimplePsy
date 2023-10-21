package classes;

import java.util.List;

public class Specialist {

    private final String id;
    private final String name;
    private final String surname;
    private String status;
    private String specialization;
    private List<String> certificates;
    private List<String> contacts;
    private int workExperience;
    private double rating;

    public Specialist(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public Double getRating() {
        return rating;
    }
}
