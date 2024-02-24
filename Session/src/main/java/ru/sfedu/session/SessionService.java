package ru.sfedu.session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    SessionRepository sessionRepository;

    public List<Session> findByDate(String start_date, String end_date, String specialist_id) {
        return sessionRepository.findSessionsByDateBetweenAndClientId(start_date, end_date, specialist_id).orElse(null);
    }
    public JsonArray findAllBySpecialistId(String specialist_id) {
        List<Session> sessions = sessionRepository.findAllByClientId(specialist_id).orElse(null);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(sessions).getAsJsonArray();

        System.out.println("Got the sessions jsonArray in SessionService, \n" +
                "in method findAllBySpecialistId" + jsonArray);

        return jsonArray;
    }
    public void createSession(String client_id, String date) {
        Session session = new Session(client_id, date);
        sessionRepository.save(session);
    }

}
