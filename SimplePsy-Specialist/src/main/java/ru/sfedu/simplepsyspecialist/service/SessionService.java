package ru.sfedu.simplepsyspecialist.service;

import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.repo.SessionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    SessionRepository sessionRepository;
    ClientService clientService;

    public SessionService(SessionRepository sessionRepository, ClientService clientService) {
        this.sessionRepository = sessionRepository;
        this.clientService = clientService;
    }

    public List<Session> findByDate(String start_date, String end_date, String specialist_id) {
        return sessionRepository.findSessionsByDateBetweenAndSpecialistId(start_date, end_date, specialist_id).orElse(null);
    }
    public List<LocalDate> findAllBySpecialistId(String specialist_id) {
        System.out.println("received specialist_id: " + specialist_id);
        List<Session> sessions = sessionRepository.findAllBySpecialistId(specialist_id).orElse(null);
        List<LocalDate> listOfSessions = new ArrayList<>();

        for (int i = 0; i < sessions.size(); i++) {
                listOfSessions.add(sessions.get(i).getDate());
            System.out.println(sessions.get(i).getDate());
        }
        System.out.println("sessions's list: " + sessions);
        return listOfSessions;
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }

    public List<Session> getAllBySpecialistId(String specialistId) {
        List<Session> sessions = sessionRepository.findAllBySpecialistId(specialistId).get();
        List<Session> sessionDTOS = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println(sessions.get(i).getDate());
            Session session = sessions.get(i);
            session.setClientId(sessions.get(i).getClientId());
            sessionDTOS.add(session);
        }
        return sessionDTOS;
    }
    public Client getClientById(String clientId)
    {
        System.out.println("ClientDTO id: " + clientId);
        Client client = clientService.findById(clientId);
        return client;
    }

    public Session findById(String sessionId) {
        return sessionRepository.findById(sessionId).get();
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public List<Session> findAllSessionsBySpecialistId(String specialistId) {
        List<Session> allASessions = sessionRepository.findAll();
        List<Session> sessions = new ArrayList<>();
        for (int i = 0; i < allASessions.size(); i++) {
            System.out.println("session from allSessions: " + allASessions.get(i).getId());
            if (allASessions.get(i).getSpecialistId().equals(specialistId)) {
                System.out.println("adding " + allASessions.get(i).getId());
                sessions.add(allASessions.get(i));
            }
        }
        return sessions;
    }

    public void updateSession(Session session) {
        String id = session.getId();
        Session oldSession = sessionRepository.findById(id).get();
        session.setId(oldSession.getId());
        if (oldSession.getProjectiveMethods() != null) {
            session.addProjectiveMethods(oldSession.getProjectiveMethods());
        }
        sessionRepository.save(session);
    }
}
