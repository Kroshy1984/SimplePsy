package classes.Meeting;

import java.time.LocalDateTime;

public class Meeting {
    private LocalDateTime lastMeetingDateTime;
    private LocalDateTime nextMeetingDateTime;
    private String meetingFormat;
    private String clientRequest;
    private String therapistState;
    private String topicsDiscussed;
    private String clientInsights;
    private String clientEmotions;
    private String therapistEmotions;
    private String therapistUnexpressedEmotions;
    private String techniquesAndMethods;
    private String obstaclesAndResistance;
    private String therapistStateAfterSession;
    private String planForNextSession;
    private String difficultiesAndSupervisionTopics;
    private boolean postponed;
    private boolean countertransference;

    public Meeting(LocalDateTime lastMeetingDateTime, LocalDateTime nextMeetingDateTime, String meetingFormat) {
        this.lastMeetingDateTime = lastMeetingDateTime;
        this.nextMeetingDateTime = nextMeetingDateTime;
        this.meetingFormat = meetingFormat;
    }

    public LocalDateTime getLastMeetingDateTime() {
        return lastMeetingDateTime;
    }

    public void setLastMeetingDateTime(LocalDateTime lastMeetingDateTime) {
        this.lastMeetingDateTime = lastMeetingDateTime;
    }

    public LocalDateTime getNextMeetingDateTime() {
        return nextMeetingDateTime;
    }

    public void setNextMeetingDateTime(LocalDateTime nextMeetingDateTime) {
        this.nextMeetingDateTime = nextMeetingDateTime;
    }

    public String getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(String meetingFormat) {
        this.meetingFormat = meetingFormat;
    }

    public String getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(String clientRequest) {
        this.clientRequest = clientRequest;
    }

    public String getTherapistState() {
        return therapistState;
    }

    public void setTherapistState(String therapistState) {
        this.therapistState = therapistState;
    }

    public String getTopicsDiscussed() {
        return topicsDiscussed;
    }

    public void setTopicsDiscussed(String topicsDiscussed) {
        this.topicsDiscussed = topicsDiscussed;
    }

    public String getClientInsights() {
        return clientInsights;
    }

    public void setClientInsights(String clientInsights) {
        this.clientInsights = clientInsights;
    }

    public String getClientEmotions() {
        return clientEmotions;
    }

    public void setClientEmotions(String clientEmotions) {
        this.clientEmotions = clientEmotions;
    }

    public String getTherapistEmotions() {
        return therapistEmotions;
    }

    public void setTherapistEmotions(String therapistEmotions) {
        this.therapistEmotions = therapistEmotions;
    }

    public String getTherapistUnexpressedEmotions() {
        return therapistUnexpressedEmotions;
    }

    public void setTherapistUnexpressedEmotions(String therapistUnexpressedEmotions) {
        this.therapistUnexpressedEmotions = therapistUnexpressedEmotions;
    }

    public String getTechniquesAndMethods() {
        return techniquesAndMethods;
    }

    public void setTechniquesAndMethods(String techniquesAndMethods) {
        this.techniquesAndMethods = techniquesAndMethods;
    }

    public String getObstaclesAndResistance() {
        return obstaclesAndResistance;
    }

    public void setObstaclesAndResistance(String obstaclesAndResistance) {
        this.obstaclesAndResistance = obstaclesAndResistance;
    }

    public String getTherapistStateAfterSession() {
        return therapistStateAfterSession;
    }

    public void setTherapistStateAfterSession(String therapistStateAfterSession) {
        this.therapistStateAfterSession = therapistStateAfterSession;
    }

    public String getPlanForNextSession() {
        return planForNextSession;
    }

    public void setPlanForNextSession(String planForNextSession) {
        this.planForNextSession = planForNextSession;
    }

    public String getDifficultiesAndSupervisionTopics() {
        return difficultiesAndSupervisionTopics;
    }

    public void setDifficultiesAndSupervisionTopics(String difficultiesAndSupervisionTopics) {
        this.difficultiesAndSupervisionTopics = difficultiesAndSupervisionTopics;
    }

    public boolean isPostponed() {
        return postponed;
    }

    public void setPostponed(boolean postponed) {
        this.postponed = postponed;
    }

    public boolean isCountertransference() {
        return countertransference;
    }

    public void setCountertransference(boolean countertransference) {
        this.countertransference = countertransference;
    }
}
