package classes;

public class Patient {

    private String firstName;
    private String lastName;
    private int age;
    private final String GENDER;
    private String diagnosis;
    private String role;
    private final int ID;

    public Patient(String firstName, String lastName, int age, String GENDER, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.GENDER = GENDER;
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return GENDER;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getID() {
        return ID;
    }
}

