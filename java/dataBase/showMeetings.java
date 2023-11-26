package java.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class showMeetings {

    public static void main(String[] args) {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";
        Connection conn;
        Statement stat;

        //Date period
        String startDate = "your_date_value_1";
        String finishDate = "your_date_value_2";

        DateFormat dateFormat = new SimpleDateFormat("dd--MM--yyyy");

        //checking date to be correct
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(startDate);
            dateFormat.parse(finishDate);
        }
        catch (ParseException e){
            System.out.println("Invalid date");
        }

        //creating sql request
        String showMeetingsInPeriod = "SELECT * FROM TABLE meeting WHERE nextMeetingDateTime BETWEEN " + startDate + " AND " + finishDate + ";";

        try {
            conn = DriverManager.getConnection(url, username, password);
            stat = conn.createStatement();
            stat.executeUpdate(showMeetingsInPeriod);
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}