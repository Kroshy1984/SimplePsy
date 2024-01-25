package ru.sfedu.simplepsyspecialist.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sfedu.simplepsyspecialist.entity.Specialist;

import java.util.Collection;
import java.util.List;

/**
 * UserDetailsImpl implementing the UserDetails interface
 */
@Getter @Setter
public class UserDetailsImpl implements UserDetails {
    private String id;
    private String email;
    private String surname;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(String id, String email, String surname, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.surname = surname;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(Specialist specialist)
    {
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(specialist.getSpecialistRole().name()));
        return new UserDetailsImpl(
                specialist.getId(),
                specialist.getEmail(),
                specialist.getSurname(),
                specialist.getPassword(),
                authorityList);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
