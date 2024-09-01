package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.entity.nested.ProjectiveMethod;
import ru.sfedu.simplepsyspecialist.entity.nested.Report;
import ru.sfedu.simplepsyspecialist.service.ClientService;
import ru.sfedu.simplepsyspecialist.service.SessionService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/SimplePsy/V1/session")
public class SessionController {

    SessionService sessionService;
    SpecialistService specialistService;
    ClientService clientService;

    public SessionController(SessionService sessionService,
                             SpecialistService specialistService,
                             ClientService clientService) {
        this.sessionService = sessionService;
        this.specialistService = specialistService;
        this.clientService = clientService;
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
    public ResponseEntity<List<LocalDate>> searchAllSessions(
            @RequestParam("specialist_id") String specialist_id) {
        System.out.println(specialist_id);
        List<LocalDate> sessions  = sessionService.findAllBySpecialistId(specialist_id);
        System.out.println("Sending JsonArray sessions back to the notification from" +
                "SessionController in method searchAllSessions: " + sessions);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/session-form")
    public String getSessionForm(Model model) {
        Session session = new Session();
        //session.setClient(new Client());
        model.addAttribute("session", session);
        return "new-front/session/session-creation";
    }

    @PostMapping("/create-session")
    public String createNewSession(Session session,
                                   @AuthenticationPrincipal UserDetails userDetails) {
//        System.out.println(session.getClient().getName());
        System.out.println("ClientId in createNewSession: " + session.getClientId());
        System.out.println(session.getPlace());
        System.out.println(session.getDate());
        //System.out.println(session.getClientId());
        String specialistId = specialistService.findByUsername(userDetails.getUsername()).getId();
        session.setSpecialistId(specialistId);
        Client client = clientService.findById(session.getClientId());
        session.setClient(client);
        sessionService.createSession(session);
//        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
//        System.out.println("Specialist found!");
//        String specialist_id = specialist.getId();
//        System.out.println(specialist_id);
//        specialistService.createNewSession(email, specialist_id, problem, session.getDate());
//        System.out.println("Session was created");
        return "redirect:/SimplePsy/V1/session/sessions";
    }

    @GetMapping("/sessions")
    public String getSessionList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Session> sessions = sessionService.getAllBySpecialistId(specialist.getId());

        /*if (specialist.getCustomerIds() == null) {
            System.out.println("No customers were found for this specialist!");
            model.addAttribute("customers", specialistCustomers);
            return "new-front/customer/customer-list";
        }*/

/*        for (int i = 0; i < specialist.getCustomerIds().size(); i++) {
            for (Session session : sessions) {
                if (Objects.equals(session.getId(), specialist.getCustomerIds().get(i))) {
                    sessions.add(session);
                    System.out.println(sessions.get(i).getDate());
                    break;
                }
            }
        }*/
        model.addAttribute("sessions", sessions);
        model.addAttribute("specialist", specialist);

        /*String specUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        model.addAttribute("specUrl", specUrl);
        List<List<Session>> meetingsByDay = specialistService.groupSessionsByDay(sessions);
        List<Session> meetingsByMonday = meetingsByDay.get(0);
        System.out.println(meetingsByMonday.size());
        List<Session> meetingsByDayTuesday = meetingsByDay.get(1);
        System.out.println(meetingsByDayTuesday.size());
        List<Session> meetingsByDayWednesday = meetingsByDay.get(2);
        System.out.println(meetingsByDayWednesday.size());
        List<Session> meetingsByDayThursday = meetingsByDay.get(3);
        System.out.println(meetingsByDayThursday.size());
        List<Session> meetingsByDayFriday = meetingsByDay.get(4);
        System.out.println(meetingsByDayFriday.size());
        List<Session> meetingsByDaySaturday = meetingsByDay.get(5);
        System.out.println(meetingsByDaySaturday.size());
        List<Session> meetingsByDaySunDay = meetingsByDay.get(6);
        System.out.println(meetingsByDaySunDay.size());
        model.addAttribute("meetingsByMonday", meetingsByMonday);
        model.addAttribute("meetingsByDayTuesday", meetingsByDayTuesday);
        model.addAttribute("meetingsByDayWednesday", meetingsByDayWednesday);
        model.addAttribute("meetingsByDayThursday", meetingsByDayThursday);
        model.addAttribute("meetingsByDayFriday", meetingsByDayFriday);
        model.addAttribute("meetingsByDaySaturday", meetingsByDaySaturday);
        model.addAttribute("meetingsByDaySunDay", meetingsByDaySunDay);
        model.addAttribute("specUrl", specUrl);*/

        return "new-front/session/sessions-list";
    }

    @GetMapping("/calendar")
    public String getCalendar(@AuthenticationPrincipal UserDetails userDetails,
                              Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        System.out.println("specialistId: " + specialist.getId());
        List<Session> sessions = sessionService.findAllSessionsBySpecialistId(specialist.getId());
        // Получаем текущий месяц и год
        YearMonth yearMonth = YearMonth.now();
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        // Генерируем список дней для отображения
        List<LocalDate> daysInMonth = IntStream.rangeClosed(1, lastDayOfMonth.getDayOfMonth())
                .mapToObj(day -> yearMonth.atDay(day))
                .collect(Collectors.toList());

        model.addAttribute("specialist", specialist);
        model.addAttribute("daysInMonth", daysInMonth);
        model.addAttribute("meetings", sessions);

        return "new-front/calendar/calendar";
    }
    @GetMapping("report/{sessionId}")
    public String getReportForm(@AuthenticationPrincipal UserDetails userDetails,
                                @PathVariable String sessionId,
                                Model model)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        Session session = sessionService.findById(sessionId);
        System.out.println("Дата встречи: " + session.getDate());
        System.out.println("Время встречи: " + session.getTimeStart() + session.getTimeFinish());
        System.out.println("Тип оплаты: " + session.getPaymentType());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = session.getDate().format(dateFormatter);
        model.addAttribute("formattedDate", formattedDate);

        model.addAttribute("projectiveMethods", session.getProjectiveMethods());
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("sessionDTO", session);
        model.addAttribute("specialist", specialist);

        if (session.getReport() == null) {
            Report report = new Report();
            model.addAttribute("report", report);
            return "new-front/session/report-create";
        }
        Report report = session.getReport();
        model.addAttribute("report", report);

        return "new-front/session/report-create";
    }
    @PostMapping("report")
    public String saveReport(@RequestParam("sessionId") String sessionId, @ModelAttribute Report report)
    {
        System.out.println(sessionId);
        Session session = sessionService.findById(sessionId);
        System.out.println(report.getRequestForSessionByClient());
        session.setReport(report);
        sessionService.createSession(session);
        return "redirect:/SimplePsy/V1/session/report/" + sessionId;
    }

    @GetMapping("report/{sessionId}/projective-method")
    public String getProjectiveMethod(@PathVariable String sessionId,
                                      Model model) {
        ProjectiveMethod projectiveMethod = new ProjectiveMethod();
        model.addAttribute("projectiveMethod", projectiveMethod);
        model.addAttribute("sessionId", sessionId);
        return "new-front/session/projective-method";
    }

    @PostMapping("report/projective-method")
    public String createProjectiveMethod(@RequestParam("sessionId") String sessionId, @ModelAttribute ProjectiveMethod projectiveMethod) {
        Session session = sessionService.findById(sessionId);
        session.setProjectiveMethods(new ArrayList<>());
        session.addProjectiveMethod(projectiveMethod);
        sessionService.updateSession(session);
        return "redirect:/SimplePsy/V1/session/report/" + sessionId;
    }

    @GetMapping("/holiday")
    public String getHolidayForm(Model model)
    {
        Session session = new Session();
        model.addAttribute("session", session);
        return "new-front/calendar/holiday";
    }
    @PostMapping("/holiday")
    public String getHolidayForm(Session session,
                                 @AuthenticationPrincipal UserDetails userDetails)
    {
        System.out.println(session.getDate());
        System.out.println(session.getEndDate());

        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        String specialistId = specialist.getId();

        session.setClientId(specialistId);
        session.setSpecialistId(specialistId);
        Client client = new Client();
        client.setName(session.getTitle());
        client.setSurname(" ");
        session.setClient(client);
        LocalDate startDate = session.getDate();
        LocalDate endDate = session.getEndDate();
        int days = endDate.getDayOfYear() - startDate.getDayOfYear();
        System.out.println(days);
        sessionService.createSession(session);
        for (int i = 1; i < days+1; i++) {
            Session nextSession = new Session();
            nextSession.setSpecialistId(session.getSpecialistId());
            nextSession.setClient(client);
            nextSession.setClientId(specialistId);
            nextSession.setTitle(session.getTitle());
            nextSession.setDate(startDate.plusDays(i));
            nextSession.setTimeStart(session.getTimeStart());
            nextSession.setTimeFinish(session.getTimeFinish());

            System.out.println(nextSession.getDate());
            sessionService.createSession(nextSession);
        }
        return "redirect:/SimplePsy/V1/session/calendar";
    }
}
