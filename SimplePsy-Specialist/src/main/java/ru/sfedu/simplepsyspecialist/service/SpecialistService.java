package ru.sfedu.simplepsyspecialist.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.entity.SpecialistRole;
import ru.sfedu.simplepsyspecialist.exception.NotFoundException;
import ru.sfedu.simplepsyspecialist.repo.SpecialistRepository;

@Service
public class SpecialistService {


    SpecialistRepository specialistRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
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
        Specialist specialist = specialistRepository.findByEmail(s.getEmail())
                .orElseThrow(() -> new NotFoundException(String.format("Specialist with email %s not found", s.getEmail())));
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
}
