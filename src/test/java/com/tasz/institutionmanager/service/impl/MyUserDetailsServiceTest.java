package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.tasz.institutionmanager.TestDataProvider.getUserEntity_Admin;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class MyUserDetailsServiceTest {

    private MyUserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new MyUserDetailsService(userRepository);
    }

    @Test
    public void loadUserByUsername_Valid() {
        when(userRepository.findByUsername("test_username"))
                .thenReturn(getUserEntity_Admin());
        final UserDetails userDetails = userDetailsService.loadUserByUsername("test_username");

        assertThat(userDetails.getUsername(), is("test_username"));
        assertThat(userDetails.getPassword(), is("test_password"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_Null() {
        userDetailsService.loadUserByUsername(null);
    }
}