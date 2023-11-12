package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PsyDB {
    /**
     * URL-Адрес БД
     */
    public static final String URL = "";

    private PsyDB() {

    }

    /**
     * Устанавливает соединение с базой данных
     *
     * @param username Имя пользователя
     * @param password Пароль пользователя
     * @return Соединение
     * @throws ClassNotFoundException драйвер postgresql не установлен
     * @throws SQLException           не удалось установить соединение с базой данных
     */
    //Также, думаю, стоит держать имя пользователя и пароль вне GitHub
    //чтобы никто кроме нас не знал как к ней подключиться
    public static Connection getConnection(final String username,
                                           final String password)
        throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, username, password);
    }
}
