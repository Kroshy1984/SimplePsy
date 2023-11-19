package ru.sfedu.simplepsy;

import java.io.*;
import java.sql.*;

import ru.sfedu.simplepsy.database.PsyDB;

public class PsyApp {

    public static void main(String[] args) {

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
