package ru.sfedu.simplepsy.classes.patient;

public class Family extends Patient{
    private String client1FullName; // Полное имя первого клиента
    private String client1Gender; // Пол первого клиента
    private String client2FullName; // Полное имя второго клиента или партнера
    private String client2Gender; // Пол второго клиента или партнера
    private String client1PhoneNumber; // Номер телефона первого клиента
    private String client2PhoneNumber; // Номер телефона второго клиента
    private String coupleStatus; // Статус пары
    private String client1RequestReason; // Причина запроса от первого клиента
    private String client2RequestReason; // Причина запроса от второго клиента
    private String client1DesiredResult; // Ожидаемый результат от первого клиента
    private String client2DesiredResult; // Ожидаемый результат от второго клиента
    private boolean cotherapyEnabled; // Флаг, определяющий, разрешена ли совместная терапия
    private String cotherapistFullName; // Полное имя со-терапевта
    private String cotherapistPhoneNumber; // Номер телефона со-терапевта
    private String feeDistribution; // Распределение оплаты

    public String getClient1FullName() {
        return client1FullName;
    }

    public void setClient1FullName(String client1FullName) {
        this.client1FullName = client1FullName;
    }

    public String getClient1Gender() {
        return client1Gender;
    }

    public void setClient1Gender(String client1Gender) {
        this.client1Gender = client1Gender;
    }

    public String getClient2FullName() {
        return client2FullName;
    }

    public void setClient2FullName(String client2FullName) {
        this.client2FullName = client2FullName;
    }

    public String getClient2Gender() {
        return client2Gender;
    }

    public void setClient2Gender(String client2Gender) {
        this.client2Gender = client2Gender;
    }

    public String getClient1PhoneNumber() {
        return client1PhoneNumber;
    }

    public void setClient1PhoneNumber(String client1PhoneNumber) {
        this.client1PhoneNumber = client1PhoneNumber;
    }

    public String getClient2PhoneNumber() {
        return client2PhoneNumber;
    }

    public void setClient2PhoneNumber(String client2PhoneNumber) {
        this.client2PhoneNumber = client2PhoneNumber;
    }

    public String getCoupleStatus() {
        return coupleStatus;
    }

    public void setCoupleStatus(String coupleStatus) {
        this.coupleStatus = coupleStatus;
    }

    public String getClient1RequestReason() {
        return client1RequestReason;
    }

    public void setClient1RequestReason(String client1RequestReason) {
        this.client1RequestReason = client1RequestReason;
    }

    public String getClient2RequestReason() {
        return client2RequestReason;
    }

    public void setClient2RequestReason(String client2RequestReason) {
        this.client2RequestReason = client2RequestReason;
    }

    public String getClient1DesiredResult() {
        return client1DesiredResult;
    }

    public void setClient1DesiredResult(String client1DesiredResult) {
        this.client1DesiredResult = client1DesiredResult;
    }

    public String getClient2DesiredResult() {
        return client2DesiredResult;
    }

    public void setClient2DesiredResult(String client2DesiredResult) {
        this.client2DesiredResult = client2DesiredResult;
    }

    public boolean getCotherapyEnabled() {
        return cotherapyEnabled;
    }

    public void setCotherapyEnabled(boolean cotherapyEnabled) {
        this.cotherapyEnabled = cotherapyEnabled;
    }

    public String getCotherapistFullName() {
        return cotherapistFullName;
    }

    public void setCotherapistFullName(String cotherapistFullName) {
        this.cotherapistFullName = cotherapistFullName;
    }

    public String getCotherapistPhoneNumber() {
        return cotherapistPhoneNumber;
    }

    public void setCotherapistPhoneNumber(String cotherapistPhoneNumber) {
        this.cotherapistPhoneNumber = cotherapistPhoneNumber;
    }

    public String getFeeDistribution() {
        return feeDistribution;
    }

    public void setFeeDistribution(String feeDistribution) {
        this.feeDistribution = feeDistribution;
    }
}
