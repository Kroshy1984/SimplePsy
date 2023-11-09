package classes.specialist;

import java.util.ArrayList;
import java.util.List;

import classes.Contact;
import classes.Doc;
import classes.client.Client;
import classes.types.*;

public class Specialist {

    private final String id; //идентификатор
    private final String name; //имя
    private final String surname; //фамилия
    private final String middleName; //отчество

    private String birthDay; //день рождения
    private String techniques; //техники (подходы к работе)
    private List<Client> clients; //массив клиентов
    private List<Certificate> certificates; //cертификаты
    private Doc docs; //документы
    private Contact contacts; //контакты
    private GenderType genderType; //пол
    private SpecializationType specializationType; //специализация

    private int workExperience; //стаж работы
    private double rating; //rating

    public Specialist(String id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.clients = new ArrayList<>();
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public void setDocs(Doc docs) {
        this.docs = docs;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public void setSpecializationType(SpecializationType specializationType) {
        this.specializationType = specializationType;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getTechniques() {
        return techniques;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public Doc getDocs() {
        return docs;
    }

    public Contact getContacts() {
        return contacts;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public SpecializationType getSpecializationType() {
        return specializationType;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public double getRating() {
        return rating;
    }

    public void addClient(Client client) {
        clients.add(client);
        client.setSpecialist(this);
    }

    public void removeClient(Client client) {
        clients.remove(client);
        client.setSpecialist(null);
    }

    public void showClients() {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
