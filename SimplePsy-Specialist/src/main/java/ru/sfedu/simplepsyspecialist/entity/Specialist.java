package ru.sfedu.simplepsyspecialist.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


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

    private List<byte[]> diplomas;
    private byte[] avatar;
    private String email;
    private String phone;

    private SpecialistRole specialistRole = SpecialistRole.USER_ROLE;

    private String notification;
    private String password;

    private List<String> customerIds;

    private String specialization;
    private String description;
    private String education;
    private String city;

//    @Valid
//    private List<ClientEntry> clients;

    public Specialist(String name, String surname, String username, String phone, String password,
                      String specialization, String description, String education, String city) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.specialization = specialization;
        this.description = description;
        this.education = education;
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCustomerIds() {
        return customerIds;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addCustomerId(String customerId) {
        if (customerIds == null) {
            customerIds = new ArrayList<>();
        }
        customerIds.add(customerId);
    }
    public void deleteCustomerById(String customerId)
    {
        customerIds.remove(customerId);
    }

    public List<byte[]> getDiplomas() {
        return diplomas;
    }

    public void addDiplomas(byte[] diploma) {
        if (diplomas == null)
            diplomas = new ArrayList<>();
        diplomas.add(diploma);
    }

    public void setDiplomas(List<byte[]> diplomas) {
        this.diplomas = diplomas;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
