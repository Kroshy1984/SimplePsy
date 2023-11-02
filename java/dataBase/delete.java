package java.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class delete {
    public static void main(String[] args) {

        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";
        Connection conn;
        Statement stat;


        String table_name = "table_name";
        String condition = "condition";
        String delete_query = "DELETE FROM " + table_name + " WHERE " + condition;

        try  {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }


        try  {
            stat = conn.createStatement();
            stat.executeQuery(delete_query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
