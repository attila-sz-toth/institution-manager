package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.dao.UsersDao;
import com.tasz.institutionmanager.model.UsersDto;
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

    private final UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final UsersDto usersDto = usersDao.findByUsername(userName);
        if (usersDto == null) {
            log.info("User not found: {}", userName);
        }

        return new MyUserPrincipal(usersDto);
    }
}
