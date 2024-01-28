//package ru.sfedu.simplepsyspecialist.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.sfedu.simplepsyspecialist.entity.Specialist;
//import ru.sfedu.simplepsyspecialist.entity.SpecialistRole;
//import ru.sfedu.simplepsyspecialist.exception.SpecialistNotFoundException;
//import ru.sfedu.simplepsyspecialist.repo.SpecialistRepository;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * UserDetailsImpl implementing the UserDetails interface
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private SpecialistRepository specialistRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(SpecialistRepository specialistRepository) {
//        this.specialistRepository = specialistRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Specialist specialist = specialistRepository.findByEmail(email)
//                .orElseThrow(() -> new SpecialistNotFoundException("Specialist with email " + email + "not found"));
//        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(specialist.getSpecialistRole().name()));
//        return new User(specialist.getEmail(), specialist.getPassword(), authorityList);
//    }
//
//
//}