package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.model.UsersDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class MyUserPrincipal implements UserDetails {
    private final UsersDto usersDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("ADMIN");

        return List.of(adminRole);
    }

    @Override
    public String getPassword() {
        log.info("Returning password in {} for user {}", this.getClass().getName(), usersDto.getUsername());
        return usersDto.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("Returning password in {} for user {}", this.getClass().getName(), usersDto.getUsername());
        return usersDto.getUsername();
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
