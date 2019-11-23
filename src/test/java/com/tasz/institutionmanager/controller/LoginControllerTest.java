package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.converter.UserConverter;
import com.tasz.institutionmanager.converter.UserDetailsConverter;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.RoleRepository;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.MessagingService;
import com.tasz.institutionmanager.service.UserAdminService;
import com.tasz.institutionmanager.service.impl.UserAdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.tasz.institutionmanager.TestDataProvider.getUserEntity_Employee;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController loginController;
    private UserAdminService userAdminService;
    private UserConverter userConverter;

    private UserDetailsConverter userDetailsConverter = new UserDetailsConverter();

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private MessagingService messagingService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private Authentication authentication;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userConverter = new UserConverter(roleRepository, institutionRepository);
        userAdminService = new UserAdminServiceImpl(
                userRepository,
                userConverter,
                userDetailsConverter,
                passwordEncoder,
                messagingService
        );
        loginController = new LoginController(userAdminService);
    }

    @Test
    public void login_Admin() throws IllegalAccessException {
        when(authentication.getName())
                .thenReturn("test_username");

        final List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
        Mockito.doReturn(simpleGrantedAuthorities)
                .when(authentication).getAuthorities();

        final Map<String, String> login = loginController.login(authentication);

        assertThat(login.get("username"), is("test_username"));
        assertThat(login.get("role"), is("ADMIN"));
        assertThat(login.get("institution"), nullValue());
    }

    @Test
    public void login_Employee() throws IllegalAccessException {
        when(authentication.getName())
                .thenReturn("test_username");

        final List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                Collections.singletonList(new SimpleGrantedAuthority("EMPLOYEE"));
        Mockito.doReturn(simpleGrantedAuthorities)
                .when(authentication).getAuthorities();

        when(userRepository.findByUsername("test_username"))
                .thenReturn(getUserEntity_Employee());

        final Map<String, String> login = loginController.login(authentication);

        assertThat(login.get("username"), is("test_username"));
        assertThat(login.get("role"), is("EMPLOYEE"));
        assertThat(login.get("institution"), is("test_institution"));
    }
}