package ru.sfedu.simplepsy.rest;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class CustomerOnboarding {
    public static void main(String[] args) {
        // Получение данных с фронта
        String customerPhone = "88005553535";
        String customerName = "John Doe";

        // Формирование JSON
        String jsonPayload = "{\"phone\":\"" + customerPhone + "\", \"name\":\"" + customerName + "\"}";

        try {
            // Установка URL REST API
            URL url = new URL("https://example.com/api/customers");

            // Создание соединения
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Установка метода запроса
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Установка заголовков запроса
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Отправка данных в тело запроса
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            // Проверка кода ответа
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                // Запись данных в таблицу прошла успешно
                System.out.println("Добавлен новый заказчик");
                System.out.println("Все хорошо!");

            } else {
                // Возникла ошибка при выполнении запроса
                System.out.println("Ошибка при добавлении заказчика");
                System.out.println("Код ошибки: " + responseCode);
            }

            // Закрытие соединения
            connection.disconnect();

        } catch (Exception e) {
            // Обработка исключений
            System.out.println("Ошибка: " + e.getMessage());
        }

        boolean customerExists = checkIfCustomerExists(customerPhone, customerName);

        if (customerExists) {
            // Заказчик уже существует, выполнение соответствующих действий
            System.out.println("Заказчик уже существует");

        } else {
            // Заказчик не существует, добавление новой записи
            boolean success = insertCustomer(customerPhone, customerName);

            if (success) {
                // Запись данных в таблицу прошла успешно
                System.out.println("Добавлен новый заказчик");
                System.out.println("Все хорошо!");

            } else {
                // Возникла ошибка при добавлении заказчика
                System.out.println("Ошибка при добавлении заказчика");
            }
        }
    }

    private static boolean checkIfCustomerExists(String name, String email) {
        // Формирование SQL запроса для проверки существования заказчика
        String sql = "SELECT COUNT(*) FROM customers WHERE name = ? AND email = ?";

        try (
                // Установка соединения с базой данных
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/path/to/database.db");

                // Создание prepared statement для выполнения SQL запроса
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // Установка параметров запроса
            statement.setString(1, name);
            statement.setString(2, email);

            // Выполнение запроса и получение результата
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            // Обработка исключений
            System.out.println("Ошибка: " + e.getMessage());
        }

        return false;
    }

    private static boolean insertCustomer(String name, String email) {
        // Формирование SQL запроса для добавления заказчика
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";

        try (
                // Установка соединения с базой данных
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/path/to/database.db");

                // Создание prepared statement для выполнения SQL запроса
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // Установка параметров запроса
            statement.setString(1, name);
            statement.setString(2, email);

            // Выполнение запроса
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            // Обработка исключений
            System.out.println("Ошибка: " + e.getMessage());
        }

        return false;
    }
}