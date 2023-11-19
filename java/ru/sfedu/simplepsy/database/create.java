package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create {
    public static void main(String[] args) {

        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";
        Connection conn;
        Statement stat;
        String create_client_table = " CREATE TABLE IF NOT EXISTS public.client (" +
                "id character varying(100)," +
                "name character varying(100)," +
                "surname character varying(100)," +
                "middlename character varying(100)," +
                "birthDay character varying(100)," +
                "meetingAddres character varying(100)," +
                "dateOffFirstContact character varying(100)," +
                "preferMeetingFormat character varying(100)," +
                "interactionPlatform character varying(100)," +
                "primeryInvoice character varying(100)," +
                "finantialConditions character varying(100)," +
                "recomendations character varying(100)--," +
                "--doc character varying(100) REFERENCES doc(...)," +
                "--contact character varying(100) REFERENCES contact(...)," +
                "--prefer_time character varying(100) REFERENCES time(...)," +
                "--status_type character varying(100) REFERENCES types(...)," +
                "--client_type character varying(100) REFERENCES types(...)," +
                "--source_type character varying(100) REFERENCES types(...)," +
                "--gender_type character varying(100) REFERENCES types(...)," +
                "--marital_status_type character varying(100) REFERENCES types(...)," +
                ")";

        try  {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }


        try  {
            stat = conn.createStatement();
            stat.executeQuery(create_client_table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
