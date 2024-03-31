package ru.sfedu.session;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.session.dto.ClientDTO;
import ru.sfedu.session.dto.SessionDTO;
import ru.sfedu.session.dto.SessionMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    SessionRepository sessionRepository;

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
        LocalDateTime resultDate = date.plusHours(3);
        Session session = new Session(resultDate, problem, specialistId, clientId);
        sessionRepository.save(session);
    }

    public List<SessionDTO> getAllBySpecialistId(String specialistId) {
        List<Session> sessions = sessionRepository.findAllBySpecialistId(specialistId).get();
        List<SessionDTO> sessionDTOS = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            SessionDTO sessionDTO = SessionMapper.INSTANCE.sessionToSessionDTO(sessions.get(i));
            sessionDTO.setClientDTO(getClientById(sessions.get(i).getClientId()));
            sessionDTOS.add(sessionDTO);
        }
        return sessionDTOS;
    }
    public ClientDTO getClientById(String clientId)
    {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8086").build();
        String url = "/SimplePsyClient/V1/client/findById";
        ClientDTO result =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("clientId", clientId)
                        .build())
                .retrieve()
                .bodyToMono(ClientDTO.class)
                .block();
        System.out.println("got the client: " + result.getName());
        return result;
    }
}
