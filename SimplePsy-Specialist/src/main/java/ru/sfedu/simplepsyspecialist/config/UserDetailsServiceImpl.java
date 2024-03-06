package ru.sfedu.simplepsyspecialist.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.exception.SpecialistNotFoundException;
import ru.sfedu.simplepsyspecialist.repo.SpecialistRepository;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    SpecialistRepository specialistRepository;

    @Autowired
    public UserDetailsServiceImpl(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("email: " + username);
        Optional<Specialist> optionalUser = specialistRepository.findByUsername(username);

        Specialist user = optionalUser.orElseThrow(() -> new SpecialistNotFoundException("User with username " + username + " not found"));

        return UserDetailsImpl.build(user);
    }
}
