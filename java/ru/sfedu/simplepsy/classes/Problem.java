package ru.sfedu.simplepsy.classes;

import classes.client.Client;

public class Problem {

    //OneToMany Example
    private Client client;
    private String clientName; // имя клиента
    private String problemDescription; // описание проблемы

    // конструктор класса
    public Problem(String clientName, String problemDescription) {
        this.clientName = clientName;
        this.problemDescription = problemDescription;
    }
    // геттеры и сеттеры для доступа к приватным полям
    public Client getClient() {
        return this.client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    // переопределение метода toString() для удобного вывода информации о проблеме
    @Override
    public String toString() {
        return "Клиент: " + clientName + "\nПроблема: " + problemDescription;
    }
}
