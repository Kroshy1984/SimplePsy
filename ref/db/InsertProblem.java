//package ru.sfedu.simplepsy.db;
//
//import java.sql.*;
//
//public class InsertProblem {
//    public static void main(String[] args) {
//        String url = "jdbc:your_database_url";
//        String username = "your_username";
//        String password = "your_password";
//
//        Connection connection;
//        Statement statement;
//
//        String customerid = '';
//        String customer_name = '';
//        String problem_description = '';
//
//        String Insert_Into_Problems = "INSERT INTO PROBLEMS (CLIENT, CLIENT_NAME, PROBLEM_DESCRIPTION) VALUES ('" + customerid + "', '" + customer_name + "', '" + problem_description + "')";
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException();
//        }
//        try {
//            statement = connection.createStatement();
//            statement.executeQuery(create_problem_table);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//}
