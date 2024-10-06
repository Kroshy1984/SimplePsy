package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Customer;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.entity.nested.PaymentType;
import ru.sfedu.simplepsyspecialist.entity.nested.ProjectiveMethod;
import ru.sfedu.simplepsyspecialist.entity.nested.Report;
import ru.sfedu.simplepsyspecialist.service.CustomerService;
import ru.sfedu.simplepsyspecialist.service.SessionService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/SimplePsy/V1/session")
public class SessionController {

    SessionService sessionService;
    SpecialistService specialistService;
    CustomerService customerService;

    public SessionController(SessionService sessionService,
                             SpecialistService specialistService,
                             CustomerService customerService) {
        this.sessionService = sessionService;
        this.specialistService = specialistService;
        this.customerService = customerService;
    }

    @GetMapping("/session-form")
    public String getSessionForm(Model model) {
        Session session = new Session();
        model.addAttribute("session", session);
        return "new-front/session/session-creation";
    }

    @PostMapping("/create-session")
    public String createNewSession(Session session,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("ClientId in createNewSession: " + session.getClientId());
        System.out.println(session.getPlace());
        System.out.println(session.getDate());
        String specialistId = specialistService.findByUsername(userDetails.getUsername()).getId();
        session.setSpecialistId(specialistId);
        Customer customer = customerService.findById(session.getClientId());
        session.setCustomer(customer);
        session.setPaymentType(PaymentType.CASHLESS);
        sessionService.createSession(session);
        return "redirect:/SimplePsy/V1/session/sessions";
    }

    @GetMapping("/sessions")
    public String getSessionList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Session> sessions = sessionService.getAllBySpecialistId(specialist.getId());

        model.addAttribute("sessions", sessions);
        model.addAttribute("specialist", specialist);

        return "new-front/session/sessions-list";
    }
    @DeleteMapping("/delete/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable String sessionId) {
        sessionService.deleteById(sessionId);
        return ResponseEntity.ok("ok");
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
    public String getProjectiveMethod(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String sessionId,
                                      Model model) {
        ProjectiveMethod projectiveMethod = new ProjectiveMethod();
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        model.addAttribute("projectiveMethod", projectiveMethod);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("specialist", specialist);
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
        Customer client = new Customer();
        client.setName(session.getTitle());
        client.setSurname(" ");
        session.setCustomer(client);
        LocalDate startDate = session.getDate();
        LocalDate endDate = session.getEndDate();
        int days = endDate.getDayOfYear() - startDate.getDayOfYear();
        System.out.println(days);
        sessionService.createSession(session);
        for (int i = 1; i < days+1; i++) {
            Session nextSession = new Session();
            nextSession.setSpecialistId(session.getSpecialistId());
            nextSession.setCustomer(client);
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
