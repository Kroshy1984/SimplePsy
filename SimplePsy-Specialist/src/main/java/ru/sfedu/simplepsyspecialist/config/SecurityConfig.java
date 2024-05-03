package ru.sfedu.simplepsyspecialist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> request
                        .requestMatchers("/SimplePsySpecialist/V1/specialist/signup").permitAll()
                        .requestMatchers("/SimplePsySpecialist/V1/specialist/find-customer").permitAll()
                        .requestMatchers("/SimplePsySpecialist/V1/specialist/find-customer/byProblemId").permitAll()
                        .anyRequest().authenticated()).
                formLogin((form) -> form.loginPage("/SimplePsySpecialist/V1/specialist/login").permitAll()
                        .defaultSuccessUrl("/SimplePsySpecialist/V1/specialist/sessions")
                        .permitAll())
                .logout((logout) -> logout.permitAll());
        return httpSecurity.build();
    }

}