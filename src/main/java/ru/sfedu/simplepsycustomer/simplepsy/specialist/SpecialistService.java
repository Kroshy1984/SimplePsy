package ru.sfedu.simplepsycustomer.simplepsy.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService {

    @Autowired
    SpecialistRepository specialistRepository;

    public Specialist save(Specialist specialist)
    {
       return specialistRepository.save(specialist);
    }
}
