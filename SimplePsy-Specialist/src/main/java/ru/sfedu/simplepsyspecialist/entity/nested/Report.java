package ru.sfedu.simplepsyspecialist.entity.nested;

import java.util.List;

public class Report {
    private String requestForSessionByClient;
    private String specialistCondition;
    private String sessionTopics;
    private String clientInsides;
    private String clientsEmotions;
    private String specialistEmotions;
    private String unmanifestedSpecialistEmotions;
    private String techniquesAndMethodsUsed;
    private String clientsSessionResistance;
    private String specialistFinalStatus;
    private String plansForNextSession;
    private String difficultiesForRequestingSuperVisa;
    private List<ProjectiveMethod> projectiveMethods;

    public Report() {
    }

    public String getRequestForSessionByClient() {
        return requestForSessionByClient;
    }

    public void setRequestForSessionByClient(String requestForSessionByClient) {
        this.requestForSessionByClient = requestForSessionByClient;
    }

    public String getSpecialistCondition() {
        return specialistCondition;
    }

    public void setSpecialistCondition(String specialistCondition) {
        this.specialistCondition = specialistCondition;
    }

    public String getSessionTopics() {
        return sessionTopics;
    }

    public void setSessionTopics(String sessionTopics) {
        this.sessionTopics = sessionTopics;
    }

    public String getClientInsides() {
        return clientInsides;
    }

    public void setClientInsides(String clientInsides) {
        this.clientInsides = clientInsides;
    }

    public String getClientsEmotions() {
        return clientsEmotions;
    }

    public void setClientsEmotions(String clientsEmotions) {
        this.clientsEmotions = clientsEmotions;
    }

    public String getSpecialistEmotions() {
        return specialistEmotions;
    }

    public void setSpecialistEmotions(String specialistEmotions) {
        this.specialistEmotions = specialistEmotions;
    }

    public String getUnmanifestedSpecialistEmotions() {
        return unmanifestedSpecialistEmotions;
    }

    public void setUnmanifestedSpecialistEmotions(String unmanifestedSpecialistEmotions) {
        this.unmanifestedSpecialistEmotions = unmanifestedSpecialistEmotions;
    }

    public String getTechniquesAndMethodsUsed() {
        return techniquesAndMethodsUsed;
    }

    public void setTechniquesAndMethodsUsed(String techniquesAndMethodsUsed) {
        this.techniquesAndMethodsUsed = techniquesAndMethodsUsed;
    }

    public String getClientsSessionResistance() {
        return clientsSessionResistance;
    }

    public void setClientsSessionResistance(String clientsSessionResistance) {
        this.clientsSessionResistance = clientsSessionResistance;
    }

    public String getSpecialistFinalStatus() {
        return specialistFinalStatus;
    }

    public void setSpecialistFinalStatus(String specialistFinalStatus) {
        this.specialistFinalStatus = specialistFinalStatus;
    }

    public String getPlansForNextSession() {
        return plansForNextSession;
    }

    public void setPlansForNextSession(String plansForNextSession) {
        this.plansForNextSession = plansForNextSession;
    }

    public String getDifficultiesForRequestingSuperVisa() {
        return difficultiesForRequestingSuperVisa;
    }

    public void setDifficultiesForRequestingSuperVisa(String difficultiesForRequestingSuperVisa) {
        this.difficultiesForRequestingSuperVisa = difficultiesForRequestingSuperVisa;
    }

    public List<ProjectiveMethod> getProjectiveMethods() {
        return projectiveMethods;
    }

    public void setProjectiveMethods(List<ProjectiveMethod> projectiveMethods) {
        this.projectiveMethods = projectiveMethods;
    }
}
