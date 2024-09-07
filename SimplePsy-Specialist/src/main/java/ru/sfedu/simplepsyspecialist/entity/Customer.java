package ru.sfedu.simplepsyspecialist.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sfedu.simplepsyspecialist.entity.nested.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Document("customer")
public class Customer {

    @Id
    private String id;
    private TypeOfClient typeOfClient;
    private String name;
    private String surname;
    private String lastName;
    private Status status;
    private Contact contact;
    private String description;
    private Sex sex;
    private List<String> problemsId;
    private TypeOfTreatment typeOfTreatment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFirstRequest;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFirstConsultation;
    private PreferredMeetingFormat preferredMeetingFormat;
    private OnlineMeetingPlace onlineMeetingPlace;
    private String offlineMeetingPlace;
    private ClientStatus clientStatus;
    private String clientsFirstRequestForTherapy;
    private String fixedTimeForMeeting;
    private String financialConditions;

    // Поля после "Подробнее"
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private int age;
    private String residentialAddress;
    private String collegialRecommendations;
    private String specialTermsOfContract;
    private FamilyStatus familyStatus;
    private PriorityCommunicationChannel priorityCommunicationChannel;
    private Supervised supervised;
    private String supervisorsName;
    private String supervisorsSurname;
    private String supervisorsLastName;
    private Contact supervisorsContact;
    private String materialForNextSessionsFromSupervision;
    private String cotherapistName;
    private String cotherapistSurname;
    private String cotherapistLastName;
    private Contact cotherapistContact;
    private String cotherapistPaymentConditions;
    private String notes;

    // Поля для пары
    private String firstClientName;
    private String firstClientSurname;
    private String firstClientLastName;
    private Contact firstClientContact;
    private Sex firstClientSex;
    private String firstClientRequestReason;
    private String firstClientDesiredResult;
    private String secondClientName;
    private String secondClientSurname;
    private String secondClientLastName;
    private Contact secondClientContact;
    private Sex secondClientSex;
    private String secondClientRequestReason;
    private String secondClientDesiredResult;

    // Поля для ребёнка
    private String childName;
    private String childSurname;
    private String childLastName;
    private Contact childContact;
    private Sex childSex;
    private String adultDesiredResult;
    private String childVisitReason;
    private String childDesireOfChange;
    private LeadsClient leadsClient;
    private String parentName;
    private String parentSurname;
    private String parentLastName;
    private Contact parentContact;
    private Sex parentSex;
    private String parentCity;
    private String firstParentsName;
    private String firstParentsSurname;
    private String firstParentsLastName;
    private Contact firstParentsContact;
    private Sex firstParentsSex;
    private String firstParentsCity;
    private String secondParentsName;
    private String secondParentsSurname;
    private String secondParentsLastName;
    private Contact secondParentsContact;
    private Sex secondParentsSex;
    private String secondParentsCity;
    private String trusteesName;
    private String trusteesSurname;
    private String trusteesLastName;
    private Contact trusteesContact;
    private Sex trusteesSex;
    private String trusteesCity;
    private List<CompletedScoring> completedScorings;
    public Customer() {
    }


    public Customer(String name, Status status, Contact contact) {
        this.name = name;
        this.status = status;
        this.contact = contact;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public Customer(String name, String surname, LocalDate dateOfBirth, Sex sex, Contact contact) throws IOException {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        this.sex = sex;
        this.contact = contact;
    }

    public Customer(String id, TypeOfClient typeOfClient, String name, String surname, String lastName, Status status,
                    Contact contact, String description, LocalDate dateOfBirth, Sex sex, List<String> problemsId,
                    TypeOfTreatment typeOfTreatment, LocalDate dateOfFirstRequest, LocalDate dateOfFirstConsultation,
                    PreferredMeetingFormat preferredMeetingFormat, OnlineMeetingPlace onlineMeetingPlace,
                    String offlineMeetingPlace, ClientStatus clientStatus, String clientsFirstRequestForTherapy,
                    String fixedTimeForMeeting, String financialConditions, String residentialAddress,
                    String collegialRecommendations, String specialTermsOfContract, FamilyStatus familyStatus,
                    PriorityCommunicationChannel priorityCommunicationChannel, Supervised supervised,
                    String supervisorsName, String supervisorsSurname, String supervisorsLastName,
                    Contact supervisorsContact, String materialForNextSessionsFromSupervision,
                    String cotherapistName, String cotherapistSurname, String cotherapistLastName, Contact cotherapistContact, String cotherapistPaymentConditions, String notes,
                    String firstClientName, String firstClientSurname, String firstClientLastName, Contact firstClientContact, Sex firstClientSex,
                    String firstClientRequestReason, String firstClientDesiredResult,
                    String secondClientName, String secondClientSurname, String secondClientLastName, Contact secondClientContact, Sex secondClientSex,
                    String secondClientRequestReason, String secondClientDesiredResult,
                    String childName, String childSurname, String childLastName, Contact childContact, Sex childSex,
                    String adultDesiredResult, String childVisitReason, String childDesireOfChange, LeadsClient leadsClient,
                    String parentName, String parentSurname, String parentLastName, Contact parentContact, Sex parentSex, String parentCity,
                    String firstParentsName, String firstParentsSurname, String firstParentsLastName, String firstParentsCity,
                    Contact firstParentsContact, Sex firstParentsSex, String secondParentsName, String secondParentsLastName, String secondParentsCity,
                    String secondParentsSurname, Contact secondParentsContact, Sex secondParentsSex,
                    String trusteesName, String trusteesSurname, String trusteesLastName, Contact trusteesContact, Sex trusteesSex, String trusteesCity) {
        this.id = id;
        this.typeOfClient = typeOfClient;
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.status = status;
        this.contact = contact;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        this.sex = sex;
        this.problemsId = problemsId;
        this.typeOfTreatment = typeOfTreatment;
        this.dateOfFirstRequest = dateOfFirstRequest;
        this.dateOfFirstConsultation = dateOfFirstConsultation;
        this.preferredMeetingFormat = preferredMeetingFormat;
        this.onlineMeetingPlace = onlineMeetingPlace;
        this.offlineMeetingPlace = offlineMeetingPlace;
        this.clientStatus = clientStatus;
        this.clientsFirstRequestForTherapy = clientsFirstRequestForTherapy;
        this.fixedTimeForMeeting = fixedTimeForMeeting;
        this.financialConditions = financialConditions;
        this.residentialAddress = residentialAddress;
        this.collegialRecommendations = collegialRecommendations;
        this.specialTermsOfContract = specialTermsOfContract;
        this.familyStatus = familyStatus;
        this.priorityCommunicationChannel = priorityCommunicationChannel;
        this.supervised = supervised;
        this.supervisorsName = supervisorsName;
        this.supervisorsSurname = supervisorsSurname;
        this.supervisorsLastName = supervisorsLastName;
        this.supervisorsContact = supervisorsContact;
        this.materialForNextSessionsFromSupervision = materialForNextSessionsFromSupervision;
        this.cotherapistName = cotherapistName;
        this.cotherapistSurname = cotherapistSurname;
        this.cotherapistLastName = cotherapistLastName;
        this.cotherapistContact = cotherapistContact;
        this.cotherapistPaymentConditions = cotherapistPaymentConditions;
        this.notes = notes;
        this.firstClientName = firstClientName;
        this.firstClientSurname = firstClientSurname;
        this.firstClientLastName = firstClientLastName;
        this.firstClientContact = firstClientContact;
        this.firstClientSex = firstClientSex;
        this.firstClientRequestReason = firstClientRequestReason;
        this.firstClientDesiredResult = firstClientDesiredResult;
        this.secondClientName = secondClientName;
        this.secondClientSurname = secondClientSurname;
        this.secondClientLastName = secondClientLastName;
        this.secondClientContact = secondClientContact;
        this.secondClientSex = secondClientSex;
        this.secondClientRequestReason = secondClientRequestReason;
        this.secondClientDesiredResult = secondClientDesiredResult;
        this.childName = childName;
        this.childSurname = childSurname;
        this.childLastName = childLastName;
        this.childContact = childContact;
        this.childSex = childSex;
        this.adultDesiredResult = adultDesiredResult;
        this.childVisitReason = childVisitReason;
        this.childDesireOfChange = childDesireOfChange;
        this.leadsClient = leadsClient;
        this.parentName = parentName;
        this.parentSurname = parentSurname;
        this.parentLastName = parentLastName;
        this.parentContact = parentContact;
        this.parentSex = parentSex;
        this.parentCity = parentCity;
        this.firstParentsName = firstParentsName;
        this.firstParentsSurname = firstParentsSurname;
        this.firstParentsLastName = firstParentsLastName;
        this.firstParentsContact = firstParentsContact;
        this.firstParentsSex = firstParentsSex;
        this.firstParentsCity = firstParentsCity;
        this.secondParentsName = secondParentsName;
        this.secondParentsSurname = secondParentsSurname;
        this.secondParentsLastName = secondParentsLastName;
        this.secondParentsContact = secondParentsContact;
        this.secondParentsSex = secondParentsSex;
        this.secondParentsCity = secondParentsCity;
        this.trusteesName = trusteesName;
        this.trusteesSurname = trusteesSurname;
        this.trusteesLastName = trusteesLastName;
        this.trusteesContact = trusteesContact;
        this.trusteesSex = trusteesSex;
        this.trusteesCity = trusteesCity;
    }


    public List<CompletedScoring> getCompletedScorings() {
        return completedScorings;
    }

    public void addCompletedScoring(CompletedScoring completedScoring) {
        if (this.completedScorings == null)
            this.completedScorings = new ArrayList<>();
        completedScorings.add(completedScoring);
    }

    public LocalDate getDateOfFirstRequest() {
        return dateOfFirstRequest;
    }

    public void setDateOfFirstRequest(LocalDate dateOfFirstRequest) {
        this.dateOfFirstRequest = dateOfFirstRequest;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getProblemsId() {
        return problemsId;
    }

    public void setProblemsId(List<String> problemsId) {
        this.problemsId = problemsId;
    }

    public TypeOfClient getTypeOfClient() {
        return typeOfClient;
    }

    public void setTypeOfClient(TypeOfClient typeOfClient) {
        this.typeOfClient = typeOfClient;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        if (dateOfBirth != null) {
            setAge(Period.between(dateOfBirth, LocalDate.now()).getYears());
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public TypeOfTreatment getTypeOfTreatment() {
        return typeOfTreatment;
    }

    public void setTypeOfTreatment(TypeOfTreatment typeOfTreatment) {
        this.typeOfTreatment = typeOfTreatment;
    }

    public LocalDate getDateOfFirstConsultation() {
        return dateOfFirstConsultation;
    }

    public void setDateOfFirstConsultation(LocalDate dateOfFirstConsultation) {
        this.dateOfFirstConsultation = dateOfFirstConsultation;
    }

    public PreferredMeetingFormat getPreferredMeetingFormat() {
        return preferredMeetingFormat;
    }

    public void setPreferredMeetingFormat(PreferredMeetingFormat preferredMeetingFormat) {
        this.preferredMeetingFormat = preferredMeetingFormat;
    }

    public OnlineMeetingPlace getOnlineMeetingPlace() {
        return onlineMeetingPlace;
    }

    public void setOnlineMeetingPlace(OnlineMeetingPlace onlineMeetingPlace) {
        this.onlineMeetingPlace = onlineMeetingPlace;
    }

    public String getOfflineMeetingPlace() {
        return offlineMeetingPlace;
    }

    public void setOfflineMeetingPlace(String offlineMeetingPlace) {
        this.offlineMeetingPlace = offlineMeetingPlace;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public String getClientsFirstRequestForTherapy() {
        return clientsFirstRequestForTherapy;
    }

    public void setClientsFirstRequestForTherapy(String clientsFirstRequestForTherapy) {
        this.clientsFirstRequestForTherapy = clientsFirstRequestForTherapy;
    }

    public String getFixedTimeForMeeting() {
        return fixedTimeForMeeting;
    }

    public void setFixedTimeForMeeting(String fixedTimeForMeeting) {
        this.fixedTimeForMeeting = fixedTimeForMeeting;
    }

    public String getFinancialConditions() {
        return financialConditions;
    }

    public void setFinancialConditions(String financialConditions) {
        this.financialConditions = financialConditions;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getCollegialRecommendations() {
        return collegialRecommendations;
    }

    public void setCollegialRecommendations(String collegialRecommendations) {
        this.collegialRecommendations = collegialRecommendations;
    }

    public String getSpecialTermsOfContract() {
        return specialTermsOfContract;
    }

    public void setSpecialTermsOfContract(String specialTermsOfContract) {
        this.specialTermsOfContract = specialTermsOfContract;
    }

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public PriorityCommunicationChannel getPriorityCommunicationChannel() {
        return priorityCommunicationChannel;
    }

    public void setPriorityCommunicationChannel(PriorityCommunicationChannel priorityCommunicationChannel) {
        this.priorityCommunicationChannel = priorityCommunicationChannel;
    }

    public Supervised getSupervised() {
        return supervised;
    }

    public void setSupervised(Supervised supervised) {
        this.supervised = supervised;
    }

    public String getSupervisorsName() {
        return supervisorsName;
    }

    public void setSupervisorsName(String supervisorsName) {
        this.supervisorsName = supervisorsName;
    }

    public String getSupervisorsSurname() {
        return supervisorsSurname;
    }

    public void setSupervisorsSurname(String supervisorsSurname) {
        this.supervisorsSurname = supervisorsSurname;
    }

    public String getSupervisorsLastName() {
        return supervisorsLastName;
    }

    public void setSupervisorsLastName(String supervisorsLastName) {
        this.supervisorsLastName = supervisorsLastName;
    }

    public Contact getSupervisorsContact() {
        return supervisorsContact;
    }

    public void setSupervisorsContact(Contact supervisorsContact) {
        this.supervisorsContact = supervisorsContact;
    }

    public String getMaterialForNextSessionsFromSupervision() {
        return materialForNextSessionsFromSupervision;
    }

    public void setMaterialForNextSessionsFromSupervision(String materialForNextSessionsFromSupervision) {
        this.materialForNextSessionsFromSupervision = materialForNextSessionsFromSupervision;
    }

    public String getCotherapistName() {
        return cotherapistName;
    }

    public void setCotherapistName(String cotherapistName) {
        this.cotherapistName = cotherapistName;
    }

    public String getCotherapistSurname() {
        return cotherapistSurname;
    }

    public void setCotherapistSurname(String cotherapistSurname) {
        this.cotherapistSurname = cotherapistSurname;
    }

    public String getCotherapistLastName() {
        return cotherapistLastName;
    }

    public void setCotherapistLastName(String cotherapistLastName) {
        this.cotherapistLastName = cotherapistLastName;
    }

    public Contact getCotherapistContact() {
        return cotherapistContact;
    }

    public void setCotherapistContact(Contact cotherapistContact) {
        this.cotherapistContact = cotherapistContact;
    }

    public String getCotherapistPaymentConditions() {
        return cotherapistPaymentConditions;
    }

    public void setCotherapistPaymentConditions(String cotherapistPaymentConditions) {
        this.cotherapistPaymentConditions = cotherapistPaymentConditions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFirstClientName() {
        return firstClientName;
    }

    public void setFirstClientName(String firstClientName) {
        this.firstClientName = firstClientName;
    }

    public String getFirstClientSurname() {
        return firstClientSurname;
    }

    public void setFirstClientSurname(String firstClientSurname) {
        this.firstClientSurname = firstClientSurname;
    }

    public String getFirstClientLastName() {
        return firstClientLastName;
    }

    public void setFirstClientLastName(String firstClientLastName) {
        this.firstClientLastName = firstClientLastName;
    }

    public Contact getFirstClientContact() {
        return firstClientContact;
    }

    public void setFirstClientContact(Contact firstClientContact) {
        this.firstClientContact = firstClientContact;
    }

    public Sex getFirstClientSex() {
        return firstClientSex;
    }

    public void setFirstClientSex(Sex firstClientSex) {
        this.firstClientSex = firstClientSex;
    }

    public String getFirstClientRequestReason() {
        return firstClientRequestReason;
    }

    public void setFirstClientRequestReason(String firstClientRequestReason) {
        this.firstClientRequestReason = firstClientRequestReason;
    }

    public String getFirstClientDesiredResult() {
        return firstClientDesiredResult;
    }

    public void setFirstClientDesiredResult(String firstClientDesiredResult) {
        this.firstClientDesiredResult = firstClientDesiredResult;
    }

    public String getSecondClientName() {
        return secondClientName;
    }

    public void setSecondClientName(String secondClientName) {
        this.secondClientName = secondClientName;
    }

    public String getSecondClientSurname() {
        return secondClientSurname;
    }

    public void setSecondClientSurname(String secondClientSurname) {
        this.secondClientSurname = secondClientSurname;
    }

    public String getSecondClientLastName() {
        return secondClientLastName;
    }

    public void setSecondClientLastName(String secondClientLastName) {
        this.secondClientLastName = secondClientLastName;
    }

    public Contact getSecondClientContact() {
        return secondClientContact;
    }

    public void setSecondClientContact(Contact secondClientContact) {
        this.secondClientContact = secondClientContact;
    }

    public Sex getSecondClientSex() {
        return secondClientSex;
    }

    public void setSecondClientSex(Sex secondClientSex) {
        this.secondClientSex = secondClientSex;
    }

    public String getSecondClientRequestReason() {
        return secondClientRequestReason;
    }

    public void setSecondClientRequestReason(String secondClientRequestReason) {
        this.secondClientRequestReason = secondClientRequestReason;
    }

    public String getSecondClientDesiredResult() {
        return secondClientDesiredResult;
    }

    public void setSecondClientDesiredResult(String secondClientDesiredResult) {
        this.secondClientDesiredResult = secondClientDesiredResult;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSurname() {
        return childSurname;
    }

    public void setChildSurname(String childSurname) {
        this.childSurname = childSurname;
    }

    public String getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(String childLastName) {
        this.childLastName = childLastName;
    }

    public Contact getChildContact() {
        return childContact;
    }

    public void setChildContact(Contact childContact) {
        this.childContact = childContact;
    }

    public Sex getChildSex() {
        return childSex;
    }

    public void setChildSex(Sex childSex) {
        this.childSex = childSex;
    }

    public String getAdultDesiredResult() {
        return adultDesiredResult;
    }

    public void setAdultDesiredResult(String adultDesiredResult) {
        this.adultDesiredResult = adultDesiredResult;
    }

    public String getChildVisitReason() {
        return childVisitReason;
    }

    public void setChildVisitReason(String childVisitReason) {
        this.childVisitReason = childVisitReason;
    }

    public String getChildDesireOfChange() {
        return childDesireOfChange;
    }

    public void setChildDesireOfChange(String childDesireOfChange) {
        this.childDesireOfChange = childDesireOfChange;
    }

    public LeadsClient getLeadsClient() {
        return leadsClient;
    }

    public void setLeadsClient(LeadsClient leadsClient) {
        this.leadsClient = leadsClient;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentSurname() {
        return parentSurname;
    }

    public void setParentSurname(String parentSurname) {
        this.parentSurname = parentSurname;
    }

    public String getParentLastName() {
        return parentLastName;
    }

    public void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }

    public Contact getParentContact() {
        return parentContact;
    }

    public void setParentContact(Contact parentContact) {
        this.parentContact = parentContact;
    }

    public Sex getParentSex() {
        return parentSex;
    }

    public void setParentSex(Sex parentSex) {
        this.parentSex = parentSex;
    }

    public String getParentCity() {
        return parentCity;
    }

    public void setParentCity(String parentCity) {
        this.parentCity = parentCity;
    }

    public String getFirstParentsName() {
        return firstParentsName;
    }

    public void setFirstParentsName(String firstParentsName) {
        this.firstParentsName = firstParentsName;
    }

    public String getFirstParentsSurname() {
        return firstParentsSurname;
    }

    public void setFirstParentsSurname(String firstParentsSurname) {
        this.firstParentsSurname = firstParentsSurname;
    }

    public String getFirstParentsLastName() {
        return firstParentsLastName;
    }

    public void setFirstParentsLastName(String firstParentsLastName) {
        this.firstParentsLastName = firstParentsLastName;
    }

    public Contact getFirstParentsContact() {
        return firstParentsContact;
    }

    public void setFirstParentsContact(Contact firstParentsContact) {
        this.firstParentsContact = firstParentsContact;
    }

    public Sex getFirstParentsSex() {
        return firstParentsSex;
    }

    public void setFirstParentsSex(Sex firstParentsSex) {
        this.firstParentsSex = firstParentsSex;
    }

    public String getFirstParentsCity() {
        return firstParentsCity;
    }

    public void setFirstParentsCity(String firstParentsCity) {
        this.firstParentsCity = firstParentsCity;
    }

    public String getSecondParentsName() {
        return secondParentsName;
    }

    public void setSecondParentsName(String secondParentsName) {
        this.secondParentsName = secondParentsName;
    }

    public String getSecondParentsSurname() {
        return secondParentsSurname;
    }

    public void setSecondParentsSurname(String secondParentsSurname) {
        this.secondParentsSurname = secondParentsSurname;
    }

    public String getSecondParentsLastName() {
        return secondParentsLastName;
    }

    public void setSecondParentsLastName(String secondParentsLastName) {
        this.secondParentsLastName = secondParentsLastName;
    }

    public Contact getSecondParentsContact() {
        return secondParentsContact;
    }

    public void setSecondParentsContact(Contact secondParentsContact) {
        this.secondParentsContact = secondParentsContact;
    }

    public Sex getSecondParentsSex() {
        return secondParentsSex;
    }

    public String getSecondParentsCity() {
        return secondParentsCity;
    }

    public void setSecondParentsCity(String secondParentsCity) {
        this.secondParentsCity = secondParentsCity;
    }

    public void setSecondParentsSex(Sex secondParentsSex) {
        this.secondParentsSex = secondParentsSex;
    }

    public String getTrusteesName() {
        return trusteesName;
    }

    public void setTrusteesName(String trusteesName) {
        this.trusteesName = trusteesName;
    }

    public String getTrusteesSurname() {
        return trusteesSurname;
    }

    public void setTrusteesSurname(String trusteesSurname) {
        this.trusteesSurname = trusteesSurname;
    }

    public String getTrusteesLastName() {
        return trusteesLastName;
    }

    public void setTrusteesLastName(String trusteesLastName) {
        this.trusteesLastName = trusteesLastName;
    }

    public Contact getTrusteesContact() {
        return trusteesContact;
    }

    public void setTrusteesContact(Contact trusteesContact) {
        this.trusteesContact = trusteesContact;
    }

    public Sex getTrusteesSex() {
        return trusteesSex;
    }

    public void setTrusteesSex(Sex trusteesSex) {
        this.trusteesSex = trusteesSex;
    }

    public String getTrusteesCity() {
        return trusteesCity;
    }

    public void setTrusteesCity(String trusteesCity) {
        this.trusteesCity = trusteesCity;
    }

    public void addProblem(String problemId) {
        if (problemsId == null) {
            problemsId = new ArrayList<>();
        }
        problemsId.add(problemId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", contact=" + contact +
                '}';
    }

        public void cleanAttributes() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value instanceof String && ((String) value).isEmpty()) {
                    field.set(this, null);
                } else if (value == null) {
                } else if (value instanceof List && ((List<?>) value).isEmpty()) {
                    field.set(this, null);
                } else if (value instanceof LocalDate && value.equals(LocalDate.of(1000, 1, 1))) {
                    field.set(this, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
