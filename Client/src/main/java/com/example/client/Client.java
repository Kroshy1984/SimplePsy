package com.example.client;

import com.example.client.nested.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document("Client")
public class Client {

    String id;
    TypeOfClient typeOfClient;
    String name;
    String surname;
    String middleName;
    Contact contact;
    Source source;
    LocalDate dateOfFirstContact;
    PreferMeetingFormat preferMeetingFormat;
    String interactionPlatform;
    String meetingAddress;
    String primaryInvoice;
    PreferTime preferTime;
    String financialConditions;
    Gender gender;
    LocalDate birthDay;
    String recommendations;
    MaritalStatus maritalStatus;
    List<String> problems;

    public Client() {
    }

    public Client(String id, String name, String surname, MaritalStatus maritalStatus, TypeOfClient typeOfClient) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.maritalStatus = maritalStatus;
        this.typeOfClient = typeOfClient;
    }

    public Client(String id, String name, String surname, Contact contact) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contact = contact;
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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public TypeOfClient getTypeOfClient() {
        return typeOfClient;
    }

    public void setTypeOfClient(TypeOfClient typeOfClient) {
        this.typeOfClient = typeOfClient;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public PreferMeetingFormat getPreferMeetingFormat() {
        return preferMeetingFormat;
    }

    public void setPreferMeetingFormat(PreferMeetingFormat preferMeetingFormat) {
        this.preferMeetingFormat = preferMeetingFormat;
    }

    public String getInteractionPlatform() {
        return interactionPlatform;
    }

    public void setInteractionPlatform(String interactionPlatform) {
        this.interactionPlatform = interactionPlatform;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getPrimaryInvoice() {
        return primaryInvoice;
    }

    public void setPrimaryInvoice(String primaryInvoice) {
        this.primaryInvoice = primaryInvoice;
    }

    public String getFinancialConditions() {
        return financialConditions;
    }

    public void setFinancialConditions(String financialConditions) {
        this.financialConditions = financialConditions;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public PreferTime getPreferTime() {
        return preferTime;
    }

    public void setPreferTime(PreferTime preferTime) {
        this.preferTime = preferTime;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDate getDateOfFirstContact() {
        return dateOfFirstContact;
    }

    public void setDateOfFirstContact(LocalDate dateOfFirstContact) {
        this.dateOfFirstContact = dateOfFirstContact;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<String> getProblems() {
        return problems;
    }

    public void addProblem(String problemId) {
        if(this.problems == null)
        {
            problems = new ArrayList<>();
        }
        problems.add(problemId);
    }
}
