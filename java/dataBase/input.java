package java.dataBase;
import java.sql.*;
import java.io.*;

public class input {
    public static void main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        Connection connection;
        Statement statement;
        String table_name = "table_name";
        String condition = "condition";
        String input_query = "INPUT INTO " + table_name + " FROM " + condition;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
        try {
            statement = connection.createStatement();
            statement.executeQuery(input_query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}