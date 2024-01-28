//package ru.sfedu.simplepsyspecialist.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import ru.sfedu.simplepsyspecialist.entity.SpecialistRole;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    UserDetailsServiceImpl userDetailsService;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.authorizeHttpRequests((request) -> request
////                        .requestMatchers("/SimplePsySpecialist/V1/specialist/signup", "/SimplePsySpecialist/V1/specialist/new").permitAll()
////                        .anyRequest().authenticated())
////                // .requestMatchers("/SimplePsySpecialist/V1/specialist/login", "/SimplePsySpecialist/V1/specialist/login").permitAll()
////                .formLogin((form) -> form.loginPage("/SimplePsySpecialist/V1/specialist/login").permitAll())
////                .logout((logout) -> logout.permitAll()).csrf(csrf -> csrf.disable());
//        httpSecurity.authorizeHttpRequests((request) -> request
//                .requestMatchers("/SimplePsySpecialist/V1/specialist/calendar").authenticated()
//                        .requestMatchers("/SimplePsySpecialist/V1/specialist/**").permitAll())
//                .csrf(csrf -> csrf.disable());
//
//        return httpSecurity.build();
//    }
//
//}
