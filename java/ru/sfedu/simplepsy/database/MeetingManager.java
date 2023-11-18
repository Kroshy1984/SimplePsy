package ru.sfedu.simplepsy.database;

import ru.sfedu.simplepsy.classes.Problem;
import ru.sfedu.simplepsy.classes.client.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetingManager {

    /**
     * Этот метод записывает новую встречу в таблицу базы данных Meetings
     * @param client клиент
     * @param problem проблема
     */
    public static void createMeeting (Client client, Problem problem) {
        // Подключение к базе данных
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO meetings (client_id, problem_id) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getId());
            statement.setString(2, problem.getId());
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
