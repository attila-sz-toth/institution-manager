package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.model.UserEntity;
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
        final UserEntity userEntity = this.userRepository.findByUsername(userName);
        if (userEntity == null) {
            log.info("UserEntity not found for: {}", userName);
            throw new UsernameNotFoundException("User Entity not found: " + userName);
        }

        return new MyUserPrincipal(userEntity);
    }
}
