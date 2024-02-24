package ru.sfedu.simplepsyspecialist.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.entity.SpecialistRole;
import ru.sfedu.simplepsyspecialist.exception.NotFoundException;
import ru.sfedu.simplepsyspecialist.exception.SpecialistNotFoundException;
import ru.sfedu.simplepsyspecialist.repo.SpecialistRepository;

import java.util.Optional;

@Service
public class SpecialistService {


    SpecialistRepository specialistRepository;
    BCryptPasswordEncoder passwordEncoder;

    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;

        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Specialist save(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    public Specialist registerNewSpecialist(Specialist specialist) {
        System.out.println(specialist.getPassword());
        String password = passwordEncoder.encode(specialist.getPassword());
        specialist.setPassword(password);
        specialist.setSpecialistRole(SpecialistRole.USER_ROLE);

        return specialistRepository.save(specialist);
    }
    public Specialist authorizeSpecialist(Specialist s)
    {

        Optional<Specialist> optionalUser = specialistRepository.findByUsername(s.getUsername());

        Specialist specialist = optionalUser.orElseThrow(() -> new SpecialistNotFoundException("User with username " + s.getUsername() + " not found"));

        if(passwordEncoder.matches(s.getPassword(), specialist.getPassword()))
        {
            return specialist;
        }
        else
        {
            throw new NotFoundException("incorrect password");
        }
    }
    public Specialist findById(String id) {
        return specialistRepository.findById(id).orElse(null);
    }
    public Specialist findByUsername(String username)
    {
        return specialistRepository.findByUsername(username).orElseThrow(() -> new SpecialistNotFoundException("User with username " + username + " not found"));
    }
    public void sendRequestToSession(String specialistId, String startDate, String endDate) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/search";
       Object result =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("specialist_id", specialistId)
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("got the result: " + result.toString());
    }
}
