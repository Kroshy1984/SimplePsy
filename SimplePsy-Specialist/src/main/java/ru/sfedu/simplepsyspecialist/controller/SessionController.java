package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.SessionService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/SimplePsySession/V1/session")
public class SessionController {

    SessionService sessionService;
    SpecialistService specialistService;

    public SessionController(SessionService sessionService, SpecialistService specialistService) {
        this.sessionService = sessionService;
        this.specialistService = specialistService;
    }

    @GetMapping("/search")
    public List<Object> handleGetRequest(
            @RequestParam("specialist_id") String specialist_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date) {
        System.out.println(specialist_id);
        System.out.println(start_date);
        System.out.println(end_date);
        List<Object> sessions = Collections.singletonList(sessionService.findByDate(start_date, end_date, specialist_id));
        System.out.println("got the first session: " + sessions.get(0).toString());
        return sessions;
    }
    @GetMapping("/searchAll")
    public ResponseEntity<List<LocalDateTime>> searchAllSessions(
            @RequestParam("specialist_id") String specialist_id) {
        System.out.println(specialist_id);
        List<LocalDateTime> sessions  = sessionService.findAllBySpecialistId(specialist_id);
        System.out.println("Sending JsonArray sessions back to the notification from" +
                "SessionController in method searchAllSessions: " + sessions);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
    @PostMapping("/new")
    public  ResponseEntity<String> createNewSession(
            @RequestParam("clientId") String clientId,
            @RequestParam("specialistId") String specialistId,
            @RequestParam("problem") String problem,
            @RequestParam("date") LocalDateTime date
           ) {
        System.out.println("clientId: " + clientId);
        System.out.println("specialistId: " + specialistId);
        System.out.println("problem: " + problem);
        System.out.println("date: " + date);
        sessionService.createSession(clientId, specialistId, problem, date);
        return ResponseEntity.ok("Session successfully created");
    }
    @GetMapping("/calendar-day")
    public ResponseEntity<List<Session>> getCalendarDayBySpecialistId(
            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Session> sessions = sessionService.getAllBySpecialistId(specialist.getId());
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
    @GetMapping("/calendar-week")
    public ResponseEntity<List<Session>> getCalendarWeekBySpecialistId(
            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Session> sessions = sessionService.getAllBySpecialistId(specialist.getId());
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
    @GetMapping("/calendar-month")
    public ResponseEntity<List<Session>> getCalendarMonthBySpecialistId(
            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Session> sessions = sessionService.getAllBySpecialistId(specialist.getId());
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}
