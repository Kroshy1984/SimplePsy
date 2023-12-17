package ru.sfedu.simplepsy.database;

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
        String create_specialist_table = "CREATE TABLE IF NOT EXISTS specialist (" +
                "ID INT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(100)," +
                "surname VARCHAR(100)," +
                "middlename VARCHAR(100)," +

                "birthDay VARCHAR(100)," +
                "techniques VARCHAR(255)," +
                "ClientsListID INT," +
                "CertificatesListID INT," +
                "DocsListID INT," +
                "ContactsListID INT," +
                "gender ENUM('MALE','FEMALE')," +
                "specialization ENUM('COUNSELLING_PSYCHOLOGIST', " +
                "'EDUCATIONAL_PSYCHOLOGIST', 'FAMILY_PSYCHOLOGIST'," +
                "'COACH', 'PSYCHOTHERAPIST', 'CLINICAL_PSYCHOLOGIST'," +
                "'PSYCHOANALYST', 'GESTALT_THERAPIST', 'ART_THERAPIST'," +
                "'HYPNOTHERAPIST', 'NLP_PSYCHOLOGIST')" +
                "FOREIGN KEY (ClientsListID) REFERENCES client(SpecialistID)," + // 1-N
                "FOREIGN KEY (CertificatesListID) REFERENCES certificates(SpecialistID)," + // 1-N
                "FOREIGN KEY (DocsListID) REFERENCES docs(SpecialistID)," + // 1-N
                "FOREIGN KEY (ContactsListID) REFERENCES contacts(SpecialistID)," + // 1-1
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
