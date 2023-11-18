package ru.sfedu.simplepsy.database;

import java.sql.*;

public class InsertProblem {
    public static void main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        Connection connection;
        Statement statement;
        String create_problem_table = "INSERT INTO PROBLEMS (CLIENT, CLIENT_NAME, PROBLEM_DESCRIPTION) VALUES (?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
        try {
            statement = connection.createStatement();
            statement.executeQuery(create_problem_table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}