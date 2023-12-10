package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

class DataProviderClient{
    private static String username;
    private static String password;


    //wrong
    public static void addClient(String name, String surname, String middleName, String birthDay, String phone){
        try(Connection connect = PsyDB.getConnection(username, password)){
            String query = "INSERT INTO client (name, surname, niddleName, birthDay, contact)" +
                    "VALUES (" + name + ", " + surname + ", " + middleName + ", " + birthDay + ", " + phone + ")";

            try{
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //как мы можем получить айди звонящего, если нам известен только его номер телефона, возможно, и ФИО?
    //может быть возвращать список пользователей с таким номером телефона и ФИО
    //или это все нужно узнать у клиента?
    public static int getUserOrCreate(String name, String surname, String middleName, String birthDay, String phone){

        try(Connection connect = PsyDB.getConnection(username, password)){
            String query = "SELECT id, name, surname, middleName FROM client" +
            "WHERE name = " + name + " AND surname = " +  surname + " AND middleName = " + middleName + " AND birthDay = " + birthDay + " AND contact = " + phone;

            try{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next() == false) {
                    addClient(name, surname, middleName, birthDay, phone);
                }
                else{
                    int id = resultSet.getInt("id");
                    return id;
                }

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DataProviderClient.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataProviderClient.password = password;
    }
}
