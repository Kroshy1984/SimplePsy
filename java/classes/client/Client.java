package classes.client;

import classes.Contact;
import classes.Doc;
import classes.specialist.Specialist;
import classes.types.*;

public class Client{

    private String id;
    private String name;
    private String surname;
    private String middleName;
    private String birthDay;

    private String meetingAddres;
    private String dateOfFirstContact;
    private String preferMeetingFormat;
    private String interactionPlatform;
    private String primeryInvoice;
    private String finantialConditions;
    private String recomendations;

    private Doc doc;
    private Contact contact;
    private PreferTime preferTime;
    private Specialist specialist;

    private StatusType statusType;
    private ClientType clientType;
    private SourceType sourceType;
    private GenderType genderType;
    private MaritalStatusType maritalStatusType;

    public Client(String name, String surname, String address, Doc doc){
    
        setName(name);
        setSurname(surname);
        setMeetingAddres(address);
        setDoc(doc);
        
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

    public String getBirthDay() {
    
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
    
        this.birthDay = birthDay;
        
    }

    public String getMeetingAddres() {
    
        return meetingAddres;
    }

    public void setMeetingAddres(String meetingAddres) {
    
        this.meetingAddres = meetingAddres;
        
    }

    public String getDateOfFirstContact() {
    
        return dateOfFirstContact;
    }

    public void setDateOfFirstContact(String dateOfFirstContact) {
    
        this.dateOfFirstContact = dateOfFirstContact;
        
    }

    public String getPreferMeetingFormat() {
    
        return preferMeetingFormat;
    }

    public void setPreferMeetingFormat(String preferMeetingFormat) {
    
        this.preferMeetingFormat = preferMeetingFormat;
        
    }

    public String getInteractionPlatform() {
    
        return interactionPlatform;
    }

    public void setInteractionPlatform(String interactionPlatform) {
    
        this.interactionPlatform = interactionPlatform;
        
    }

    public String getPrimeryInvoice() {
    
        return primeryInvoice;
    }

    public void setPrimeryInvoice(String primeryInvoice) {
    
        this.primeryInvoice = primeryInvoice;
        
    }

    public String getFinantialConditions() {
    
        return finantialConditions;
    }

    public void setFinantialConditions(String finantialConditions) {
    
        this.finantialConditions = finantialConditions;
        
    }

    public String getRecomendations() {
    
        return recomendations;
    }

    public void setRecomendations(String recomendations) {
    
        this.recomendations = recomendations;
        
    }

    public Doc getDoc() {
    
        return doc;
    }

    public void setDoc(Doc doc) {
    
        this.doc = doc;
        
    }

    public Contact getContact() {
    
        return contact;
    }

    public void setContact(Contact contact) {
    
        this.contact = contact;
        
    }

    public PreferTime getPreferTime() {
    
        return preferTime;
    }

    public void setPreferTime(PreferTime preferTime) {
    
        this.preferTime = preferTime;
        
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public StatusType getStatusType() {
    
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
    
        this.statusType = statusType;
        
    }

    public ClientType getClientType() {
    
        return clientType;
    }

    public void setClientType(ClientType clientType) {
    
        this.clientType = clientType;
        
    }

    public SourceType getSourceType() {
    
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
    
        this.sourceType = sourceType;
        
    }

    public GenderType getGenderType() {
    
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
    
        this.genderType = genderType;
        
    }

    public MaritalStatusType getMaritalStatusType() {
    
        return maritalStatusType;
    }

    public void setMaritalStatusType(MaritalStatusType maritalStatusType) {
    
        this.maritalStatusType = maritalStatusType;
        
    }
    
}
