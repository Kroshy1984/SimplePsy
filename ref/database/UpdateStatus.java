package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class UpdateStatus {
    public static void  main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        Connection conn;
        Statement stat;

        // Обновление статуса пациента на спящий
        String updatePatientStatusQuery =
                "UPDATE PatientTable " +
                "SET patient_status = 'sleeping', therapy_results = 'какой-либо результат' " +
                "WHERE patient_status = 'active' " +
                "AND NOT EXISTS (SELECT * FROM meetings WHERE id = id AND meeting_status = 'scheduled')";

        // Обновление статуса клиента на спящий
        String updateClientStatusQuery =
                "UPDATE ClientTable " +
                "SET client_status = 'sleeping', therapy_results = 'какой-либо результат' " +
                "WHERE client_status = 'active' " +
                "AND NOT EXISTS (SELECT * FROM meetings WHERE id = id AND meeting_status = 'scheduled')";


        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        try {
            stat = conn.createStatement();
            stat.executeUpdate(updatePatientStatusQuery);
            stat.executeUpdate(updateClientStatusQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
