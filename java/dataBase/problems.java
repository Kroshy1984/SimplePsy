package java.dataBase;
import java.sql.*;

public class problems {
    public static void main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        Connection connection;
        Statement statement;
        String create_problem_table = "CREATE TABLE PROBLEMS " +
                " CLIENT character varying(100) NOT NULL," +
                " CLIENT_NAME character varying(100) NOT NULL, " +
                " PROBLEM_DESCRIPTION character varying(100) NOT NULL" +
                "--document_name character varying(100) REFERENCES document(...), " +
                "--contact character varying(100) REFERENCES contact(...), " +
                "--prefer_time character varying(100) REFERENCES time(...), " +
                "--status_type character varying(100) REFERENCES types(...), " +
                "--client_type character varying(100) REFERENCES types(...), " +
                "--source_type character varying(100) REFERENCES types(...), " +
                "--gender_type character varying(100) REFERENCES types(...), " +
                "--marital_status_type character varying(100) REFERENCES types(...)";
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