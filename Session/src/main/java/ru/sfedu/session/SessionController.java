package ru.sfedu.session;

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
        List<Object> sessions  = Collections.singletonList(sessionService.findByDate(start_date, end_date, specialist_id));
        System.out.println("got the first session: " + sessions.get(0));
        return sessions;
    }

    @PostMapping("/new")
    public void handlePostRequest(
            @RequestParam("specialist_id") String specialist_id,
            @RequestParam("date") String date) {
        sessionService.createSession(specialist_id, date);
    }

}
