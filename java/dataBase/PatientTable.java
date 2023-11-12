package java.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientTable{
    public static void main(String[] args) {

        // Подключение к базе данных
        String url = "jdbc:your_database_name"; // URL базы данных
        String username = "your_username"; // Имя пользователя базы данных
        String password = "your_password"; // Пароль пользователя базы данных

        Connection conn; // Переменная для хранения соединения с базой данных
        Statement stat; // Переменная для выполнения SQL-запросов

// Создание таблицы пациента (id - первичный ключ. В качестве id можно использовать СНИЛС пациента)
        String CreatePatientTable = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT PRIMARY KEY," + // Столбец id, первичный ключ
                "name character varying(100)," +
                "surname character varying(100)," +
                "middle_name character varying(100)," +
                "birth_day character varying(100)," +
                "meeting_address character varying(100)," +
                "date_of_first_contact character varying(100)," +
                "prefer_meeting_format character varying(100)," +
                "interaction_platform character varying(100)," +
                "primary_invoice character varying(100)," +
                "financial_conditions character varying(100)," +
                "recommendations character varying(100)," +
                "doc_id INT," +
                "contact_id INT," +
                "prefer_time_id INT," +
                "status_type character varying(100)," +
                "client_type character varying(100)," +
                "source_type character varying(100)," +
                "gender_type character varying(100)," +
                "marital_status_type character varying(100)," + // Столбец marital_status_type, тип данных - character varying(100)
                "FOREIGN KEY (doc_id) REFERENCES doctors(id)," + // Внешний ключ doc_id, ссылается на столбец id таблицы doctors
                "FOREIGN KEY (contact_id) REFERENCES contacts(id)," + // Внешний ключ contact_id, ссылается на столбец id таблицы contacts
                "FOREIGN KEY (prefer_time_id) REFERENCES prefer_times(id)" + // Внешний ключ prefer_time_id, ссылается на столбец id таблицы prefer_times
                ")";

        try  {
            conn = DriverManager.getConnection(url, username, password); // Устанавливаем соединение с базой данных
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        try  {
            stat = conn.createStatement(); // Создаем объект Statement для выполнения SQL-запросов
            stat.executeQuery(CreatePatientTable); // Выполняем SQL-запрос для создания таблицы patients
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}

