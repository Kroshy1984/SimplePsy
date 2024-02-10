package ru.sfedu.session;

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

    public void createSession(String client_id, String date) {
        Session session = new Session(client_id, date);
        sessionRepository.save(session);
    }

}
