package ru.sfedu.session;

import com.google.gson.JsonArray;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/SimplePsySession/V1/session")

public class SessionController {

    SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
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
        System.out.println("got the first session: " + sessions.get(0));
        return sessions;
    }
    @GetMapping("/searchAll")
    public JsonArray searchAllSessions(
            @RequestParam("specialist_id") String specialist_id) {
        System.out.println(specialist_id);
        JsonArray sessions  = sessionService.findAllBySpecialistId(specialist_id);
        System.out.println("Sending JsonArray sessions back to the notification from" +
                "SessionController in method searchAllSessions: " + sessions);
        return sessions;
    }
    @PostMapping("/new")
    public void handlePostRequest(
            @RequestParam("specialist_id") String specialist_id,
            @RequestParam("date") String date) {
        sessionService.createSession(specialist_id, date);
    }

}
