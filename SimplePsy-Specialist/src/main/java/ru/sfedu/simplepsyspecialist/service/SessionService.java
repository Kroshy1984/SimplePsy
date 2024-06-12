package ru.sfedu.simplepsyspecialist.service;

import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.repo.SessionRepository;

import java.time.LocalDateTime;
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
    public List<LocalDateTime> findAllBySpecialistId(String specialist_id) {
        System.out.println("received specialist_id: " + specialist_id);
        List<Session> sessions = sessionRepository.findAllBySpecialistId(specialist_id).orElse(null);
        List<LocalDateTime> listOfSessions = new ArrayList<>();

        for (int i = 0; i < sessions.size(); i++) {
                listOfSessions.add(sessions.get(i).getDate());
            System.out.println(sessions.get(i).getDate());
        }
        System.out.println("sessions's list: " + sessions);
        return listOfSessions;
    }
    public void createSession(String clientId, String specialistId,
                              String problem, LocalDateTime date) {
        Session session = new Session(date, problem, specialistId, clientId);
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
}
