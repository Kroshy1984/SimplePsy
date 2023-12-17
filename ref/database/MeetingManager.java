package ru.sfedu.simplepsy.database;

import ru.sfedu.simplepsy.classes.Problem;
import ru.sfedu.simplepsy.classes.client.Client;

import java.sql.*;
import java.time.LocalDateTime;

public class MeetingManager {

    /**
     * Этот метод записывает новую встречу в таблицу базы данных Meetings
     * @param client клиент
     * @param problem проблема
     */
    public static void createMeeting(Client client,
                                     Problem problem,
                                     LocalDateTime lastMeetingDateTime,
                                     LocalDateTime nextMeetingDateTime,
                                     String meetingFormat) {
        // Подключение к базе данных
        String url = "jdbc:your_database_url";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Получаем id клиента, если такой существует, из базы данных
            String queryClient = "SELECT id FROM clients WHERE id = ?";
            PreparedStatement statementClient = connection.prepareStatement(queryClient);
            statementClient.setString(1, client.getId());
            ResultSet resultSetClient = statementClient.executeQuery();

            // Получаем id проблемы, если такая существует, из базы данных
            String queryProblem = "SELECT id FROM problems WHERE id = ?";
            PreparedStatement statementProblem = connection.prepareStatement(queryProblem);
            statementProblem.setString(1, problem.getId());
            ResultSet resultSetProblem = statementProblem.executeQuery();

            if (resultSetClient.next() && resultSetProblem.next()) {
                String clientId = resultSetClient.getString("id");
                String problemId = resultSetProblem.getString("id");

                // Помещаем данные о клиенте и его проблеме в таблицу meetings
                String queryInsert = "INSERT INTO meetings (client_id, problem_id, " +
                        "last_meeting_datetime, " +
                        "next_meeting_datetime, " +
                        "meeting_format) " +
                        "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement statementInsert = connection.prepareStatement(queryInsert);
                statementInsert.setString(1, clientId);
                statementInsert.setString(2, problemId);
                statementInsert.setTimestamp(3, Timestamp.valueOf(lastMeetingDateTime));
                statementInsert.setTimestamp(4, Timestamp.valueOf(nextMeetingDateTime));
                statementInsert.setString(5, meetingFormat);
                statementInsert.executeUpdate(queryInsert);

                // Закрываем все ресурсы
                resultSetClient.close();
                resultSetProblem.close();
                statementClient.close();
                statementProblem.close();
                statementInsert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
