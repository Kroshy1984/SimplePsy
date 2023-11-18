import dataBase.PsyDB;
//import rest.WeatherApi;

import java.io.*;
import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;

public class PsyApp {

    public static void main(String[] args) {
//        String baseUrl = "http://api.weatherapi.com/v1"; //протокол + хост + версия апи
//        String apiKey = "3e00ce72746340598f371039230203"; //задаем апи ключ
//
//        WeatherApi api = new WeatherApi(baseUrl, apiKey);
//
//        String apiResource = "current";
//        String fmt = "json";
//
//        Map<String, String> params = new HashMap<>();
//        params.put("q", "London");
//        params.put("aqi", "no");
//        params.put("days", "1");
//
//        try {
//            api.sendRequest(apiResource, fmt, params);
//        } catch (IOException | InterruptedException e) {
//            System.out.println(e.getMessage());
//        }


        String ip = "";
        String database = "";
        String username = "";
        String password = "";

        String audio_file = "../rec.mp3";
        String transcribe_file = "../rec_transcribe.txt";

        PsyDB.URL = "jdbc:postgresql://" + ip + ":5432/" + database;
        try (Connection con = PsyDB.getConnection(username, password)) {
            InputStream voice = new FileInputStream(audio_file);
            BufferedReader transcrib = new BufferedReader(new FileReader(transcribe_file));
            PreparedStatement ps = con.prepareStatement("INSERT INTO records VALUES (?, ?, ?)");
            ps.setInt(1, 4);
            ps.setBinaryStream(2, voice);
            ps.setCharacterStream(3, transcrib);
            ps.execute();
            ps.close();

            ps = con.prepareStatement("SELECT audio FROM records WHERE id = ?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            byte[] audioBytes = rs.getBytes(1);
            rs.close();
            ps.close();
            System.out.println(audioBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
