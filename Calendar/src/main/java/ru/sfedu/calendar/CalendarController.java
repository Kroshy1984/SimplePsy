package ru.sfedu.calendar;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SimplePsyCalendar/V1/calendar")

public class CalendarController {
    CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/search")
    public void sendRequestToSession(
            @RequestParam String specialist_id,
            @RequestParam String start_date,
            @RequestParam String end_date) {
        System.out.println(specialist_id);
        System.out.println(start_date);
        System.out.println(end_date);
        calendarService.sendRequestToSession(specialist_id, start_date, end_date);
    }

    @PostMapping("/result")
    public void sendResultToSpecialist(@RequestParam List<Object> sessionList) {
        calendarService.sendResultToSpecialist(sessionList);
    }
}
