package ru.sfedu.simplepsyspecialist.entity.nested;

import java.time.LocalDate;

public class ProjectiveMethod {

    private String name;
    private LocalDate date;
    private byte[] image;

    public ProjectiveMethod() {}

    public ProjectiveMethod(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public ProjectiveMethod(String name, LocalDate date, byte[] image) {
        this.name = name;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
