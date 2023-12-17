package ru.sfedu.simplepsy.classes.patient;

public class Child extends Patient{
    private String parentFullName; // Полное имя родителя
    private String secondParentFullName; // Полное имя второго родителя
    private String caregiverFullName; // Полное имя человека который приводит ребенка
    private String contactNumber; // Контактный номер
    private String payerFullName; // Полное имя плательщика
    private String parentEmail; // Электронная почта родителя
    private String therapyRequestReason; // Причина запроса на терапию
    private String desiredTherapyResult; // Желаемый результат терапии
    private String explanation; // Дополнительное пояснение или комментарий
    // Поле для хранения фото рисунков и поделок ребенка
    private byte[] childArtwork;

    public String getParentFullName() {
        return parentFullName;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public String getSecondParentFullName() {
        return secondParentFullName;
    }

    public void setSecondParentFullName(String secondParentFullName) {
        this.secondParentFullName = secondParentFullName;
    }

    public String getCaregiverFullName() {
        return caregiverFullName;
    }

    public void setCaregiverFullName(String caregiverFullName) {
        this.caregiverFullName = caregiverFullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPayerFullName() {
        return payerFullName;
    }

    public void setPayerFullName(String payerFullName) {
        this.payerFullName = payerFullName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getTherapyRequestReason() {
        return therapyRequestReason;
    }

    public void setTherapyRequestReason(String therapyRequestReason) {
        this.therapyRequestReason = therapyRequestReason;
    }

    public String getDesiredTherapyResult() {
        return desiredTherapyResult;
    }

    public void setDesiredTherapyResult(String desiredTherapyResult) {
        this.desiredTherapyResult = desiredTherapyResult;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public byte[] getChildArtwork() {
        return childArtwork;
    }

    public void setChildArtwork(byte[] childArtwork) {
        this.childArtwork = childArtwork;
    }
}