package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.MyUserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        final User user = this.userRepository.findByUsername(userName);
        if (user == null) {
            log.info("UserRegistrationDetails not found: {}", userName);
            throw new UsernameNotFoundException("UserRegistrationDetails not found: " + userName);
        }

        return new MyUserPrincipal(user);
    }
}
