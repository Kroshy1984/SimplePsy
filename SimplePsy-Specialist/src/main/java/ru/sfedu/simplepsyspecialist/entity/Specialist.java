package ru.sfedu.simplepsyspecialist.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Document("Specialist")
public class Specialist {

    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-я]+^[а-я]$")
    private String name;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-Я]+^[а-я]$")
    private String surname;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-Я]+^[а-я]$")
    private String middleName;

    private String email;

    private SpecialistRole specialistRole = SpecialistRole.USER_ROLE;

    @DBRef
    @Valid
    private List<Calendar> calendar;

    private String notification;
    private String password;

//    @Valid
//    private List<ClientEntry> clients;


    public Specialist(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public Specialist(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Specialist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<Calendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<Calendar> calendar) {
        this.calendar = calendar;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public SpecialistRole getSpecialistRole() {
        return specialistRole;
    }

    public void setSpecialistRole(SpecialistRole specialistRole) {
        this.specialistRole = specialistRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
