package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class createOneToOneRelationShip {

    public static void main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";
        Connection conn;
        Statement stat;

        String tableName1 = "your_table_name_1";
        String primaryKeyTable1 = "your_primary_key_1";
        String otherParamsTable1 = "your_other_params_1";

        String tableName2 = "your_table_name_2";
        String primaryKeyTable2 = "your_primary_key_2";
        String otherParamsTable2 = "your_other_params_2";

        try {
            conn = DriverManager.getConnection(url, username, password);
            stat = conn.createStatement();
            String sql = "CREATE TABLE " + tableName1 + " (" + primaryKeyTable1 + " PRIMARY KEY NOT NULL, " + otherParamsTable1 + ")";
            stat.executeUpdate(sql);
            sql = "CREATE TABLE " + tableName2 + " (" + primaryKeyTable2 + " PRIMARY KEY NOT NULL, " + otherParamsTable2 + " " +
                    "____ NOT NULL UNIQUE REFERENCES "+ tableName1 +"("+ primaryKeyTable1 +"))";
            stat.executeUpdate(sql);
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
