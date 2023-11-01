

import java.sql.*;

public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/mydb";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connected to db");
            }

            Statement statement = connection.createStatement();

            //statement.execute("INSERT INTO users (name, age, email) VALUES('Vanya', 21, 'vanya@gmail.com');");

            ResultSet res = statement.executeQuery("SELECT * FROM users");

            while (res.next()){
                int id = res.getInt(1);
                System.out.print("id: " + id + ", ");
                String name = res.getString(2);
                System.out.print("name: " + name + ", ");
                int age = res.getInt(3);
                System.out.print("age: " + age + ", ");
                String email = res.getString(4);
                System.out.println("email: " + email + ";");
            }

            connection.close();
            if(connection.isClosed()){
                System.out.println("Disconnected from db");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

}