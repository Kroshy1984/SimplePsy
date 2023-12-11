package java.dataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetClientInfo
{
    public static void main(String[] args)
    {
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        Connection connection;
        Statement statement;

        String infoText = "Иван Иванович Иванов +71234567890";

        String namePattern = "([\\p{L} -]+)";
        String phonePattern = "(?:\\+7|8)\\d{10}";

        Pattern nameRegex = Pattern.compile(namePattern);
        Matcher nameMatcher = nameRegex.matcher(infoText);
        String name = "";

        if (nameMatcher.find())
        {
            name = nameMatcher.group().trim();
        }

        Pattern phoneRegex = Pattern.compile(phonePattern);
        Matcher phoneMatcher = phoneRegex.matcher(infoText);
        String phoneNumber = "";

        if (phoneMatcher.find())
        {
            phoneNumber = phoneMatcher.group();
        }

        if (!name.isEmpty())
            System.out.println("Найдено имя: " + name);
        else
            System.out.println("Имя не найдено");

        if (!phoneNumber.isEmpty())
            System.out.println("Найден номер телефона: " + phoneNumber);
        else
            System.out.println("Номер телефона не найден");

        String info = "SELECT * FROM CLIENT WHERE name = " + name + " OR CONTACT = " + phoneNumber;

        try
        {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        try
        {
            statement = connection.createStatement();
            statement.executeQuery(info);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
