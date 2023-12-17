package ru.sfedu.simplepsy;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer")
public class SampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mongoURI = "mongodb://psyApp:123@93.95.97.176";
        try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
            long docCount = mongoClient.getDatabase("psyDb").getCollection("customer").countDocuments();
            resp.getWriter().append(Long.toString(docCount));
        }

    }

}
