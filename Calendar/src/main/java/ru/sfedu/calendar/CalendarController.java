package ru.sfedu.calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/result")
    public void sendResultToSpecialist(@RequestParam("sessionList") List<Object> sessionList) {
        System.out.println("got the sessionList " + sessionList.get(0));
        calendarService.sendResultToSpecialist(sessionList);
    }
//    @GetMapping("/calendar")
//    public ResponseEntity<> findAll(String specialistId)
//    {
//        calendarService.getAllSessionsBySpecialistId(specialistId);
//    }
}
