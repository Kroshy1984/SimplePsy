package ru.sfedu.simplepsyspecialist.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsyspecialist.entity.nested.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
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
    private Status status; // TODO: Решить нужно ли это поле
    private Contact contact;
    private String description;
    private Sex sex;
    private List<String> problemsId;
    private TypeOfTreatment typeOfTreatment;
    private LocalDate dateOfFirstRequest;
    private LocalDate dateOfFirstConsultation;
    private PreferredMeetingFormat preferredMeetingFormat;
    private OnlineMeetingPlace onlineMeetingPlace;
    private String offlineMeetingPlace;
    private ClientStatus clientStatus;
    private String clientsFirstRequestForTherapy;
    private String fixedTimeForMeeting;
    private String financialConditions;
    // вторая форма
    private LocalDate dateOfBirth;
    private String residentialAddress;
    private String collegialRecommendations;
    private String specialTermsOfContract;
    private FamilyStatus familyStatus;
    private PriorityCommunicationChannel priorityCommunicationChannel;
    private boolean isSupervision;
    private String supervisorsName;
    private String supervisorsSurname;
    private Contact supervisorsContact;
    private String materialForNextSessionsFromSupervision;
    private String notes;
    // private LocalDate dateOfRegistration = LocalDate.of(1000, 1 , 1);
    // Поля для пары
    private String partnersName;
    private String partnersSurname;
    private Contact partnersContact;
    private Sex partnersSex;
    // Поля для ребёнка
    private LeadsClient leadsClient;
    private String firstParentsName;
    private String firstParentsSurname;
    private Contact firstParentsContact;
    private Sex firstParentsSex;
    private String secondParentsName;
    private String secondParentsSurname;
    private Contact secondParentsContact;
    private Sex secondParentsSex;
    private String trusteesName;
    private String trusteesSurname;
    private Contact trusteesContact;
    private Sex trusteesSex;

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
        this.sex = sex;
        this.contact = contact;
    }

    public Customer(String id, TypeOfClient typeOfClient, String name, String surname, Status status, Contact contact,
                    String description, LocalDate dateOfBirth, Sex sex, List<String> problemsId,
                    TypeOfTreatment typeOfTreatment, LocalDate dateOfFirstRequest, LocalDate dateOfFirstConsultation,
                    PreferredMeetingFormat preferredMeetingFormat, OnlineMeetingPlace onlineMeetingPlace,
                    String offlineMeetingPlace, ClientStatus clientStatus, String clientsFirstRequestForTherapy,
                    String fixedTimeForMeeting, String financialConditions, String residentialAddress,
                    String collegialRecommendations, String specialTermsOfContract, FamilyStatus familyStatus,
                    PriorityCommunicationChannel priorityCommunicationChannel, boolean isSupervision,
                    String supervisorsName, String supervisorsSurname, Contact supervisorsContact,
                    String materialForNextSessionsFromSupervision, String notes, String partnersName,
                    String partnersSurname, Contact partnersContact, Sex partnersSex, LeadsClient leadsClient,
                    String firstParentsName, String firstParentsSurname, Contact firstParentsContact,
                    Sex firstParentsSex, String secondParentsName, String secondParentsSurname,
                    Contact secondParentsContact, Sex secondParentsSex, String trusteesName, String trusteesSurname,
                    Contact trusteesContact, Sex trusteesSex) {
        this.id = id;
        this.typeOfClient = typeOfClient;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.contact = contact;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
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
        this.isSupervision = isSupervision;
        this.supervisorsName = supervisorsName;
        this.supervisorsSurname = supervisorsSurname;
        this.supervisorsContact = supervisorsContact;
        this.materialForNextSessionsFromSupervision = materialForNextSessionsFromSupervision;
        this.notes = notes;
        this.partnersName = partnersName;
        this.partnersSurname = partnersSurname;
        this.partnersContact = partnersContact;
        this.partnersSex = partnersSex;
        this.leadsClient = leadsClient;
        this.firstParentsName = firstParentsName;
        this.firstParentsSurname = firstParentsSurname;
        this.firstParentsContact = firstParentsContact;
        this.firstParentsSex = firstParentsSex;
        this.secondParentsName = secondParentsName;
        this.secondParentsSurname = secondParentsSurname;
        this.secondParentsContact = secondParentsContact;
        this.secondParentsSex = secondParentsSex;
        this.trusteesName = trusteesName;
        this.trusteesSurname = trusteesSurname;
        this.trusteesContact = trusteesContact;
        this.trusteesSex = trusteesSex;
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

    public boolean isSupervision() {
        return isSupervision;
    }

    public void setSupervision(boolean supervision) {
        isSupervision = supervision;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPartnersName() {
        return partnersName;
    }

    public void setPartnersName(String partnersName) {
        this.partnersName = partnersName;
    }

    public String getPartnersSurname() {
        return partnersSurname;
    }

    public void setPartnersSurname(String partnersSurname) {
        this.partnersSurname = partnersSurname;
    }

    public Contact getPartnersContact() {
        return partnersContact;
    }

    public void setPartnersContact(Contact partnersContact) {
        this.partnersContact = partnersContact;
    }

    public Sex getPartnersSex() {
        return partnersSex;
    }

    public void setPartnersSex(Sex partnersSex) {
        this.partnersSex = partnersSex;
    }

    public LeadsClient getLeadsClient() {
        return leadsClient;
    }

    public void setLeadsClient(LeadsClient leadsClient) {
        this.leadsClient = leadsClient;
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

    public Contact getSecondParentsContact() {
        return secondParentsContact;
    }

    public void setSecondParentsContact(Contact secondParentsContact) {
        this.secondParentsContact = secondParentsContact;
    }

    public Sex getSecondParentsSex() {
        return secondParentsSex;
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
