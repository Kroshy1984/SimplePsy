package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSpecialist {
    public static void main(String[] args) {

        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";
        Connection conn;
        Statement stat;
        String create_certificate_table = "CREATE TABLE IF NOT EXISTS certificates (" +
                "ID INT NOT NULL AUTO_INCREMENT, " +
                "SpecialistID INT," +
                "Number VARCHAR(100)," +
                "Date DATE," +
                "Theme VARCHAR(100)," +
                "PRIMARY KEY (ID)" +
                ")";

        // Можно ли тут использовать number для FOREIGN KEY? Или с id лучше?
        String create_docs_table = "CREATE TABLE IF NOT EXISTS docs (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "SpecialistID INT," +
                "doctype ENUM('Passport','Licence')," +
                "number VARCHAR(20)," +
                "serial VARCHAR(20)," +
                "PRIMARY KEY (ID)" +
                ")";

        // Возможно лучше будет не делать отледьную таблицу для контактов, а добавить прямо phone и email в таблицу Specialist?
        String create_contacts_table = "CREATE TABLE IF NOT EXISTS contacts (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "SpecialistID INT," +
                "phone VARCHAR(15)," +
                "email VARCHAR(256)," +
                "PRIMARY KEY (ID)" +
                ")";

        String create_specialist_table = "CREATE TABLE IF NOT EXISTS specialist (" +
                "ID VARCHAR(100) NOT NULL PRIMARY KEY," +
                "name VARCHAR(100)," +
                "surname VARCHAR(100)," +
                "middlename VARCHAR(100)," +
                "birthDay VARCHAR(100)," +
                "techniques VARCHAR(255)," +
                "/*ClientsListID INT,*/" +
                "CertificatesListID INT," +
                "DocsListID INT," +
                "ContactsListID INT," +
                "/*FOREIGN KEY (ClientsListID) REFERENCES public.client,*/" +
                "FOREIGN KEY (CertificatesListID) REFERENCES certificates(SpecialistID)," +
                "FOREIGN KEY (DocsListID) REFERENCES docs(SpecialistID)," +
                "FOREIGN KEY (ContactsListID) REFERENCES contacts(SpecialistID)," +
                "gender ENUM('MALE','FEMALE')," +
                "specialization ENUM('COUNSELLING_PSYCHOLOGIST', " +
                "'EDUCATIONAL_PSYCHOLOGIST', 'FAMILY_PSYCHOLOGIST'," +
                " 'COACH', 'PSYCHOTHERAPIST', 'CLINICAL_PSYCHOLOGIST'," +
                " 'PSYCHOANALYST', 'GESTALT_THERAPIST', 'ART_THERAPIST'," +
                " 'HYPNOTHERAPIST', 'NLP_PSYCHOLOGIST')" +
                ")";

        try  {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }


        try  {
            stat = conn.createStatement();
            stat.execute(create_certificate_table);
            stat.execute(create_docs_table);
            stat.execute(create_contacts_table);
            stat.execute(create_specialist_table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

    }
}
