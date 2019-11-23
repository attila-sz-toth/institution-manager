package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.model.RoleEntity;
import com.tasz.institutionmanager.model.UserEntity;
import org.junit.Test;

import static com.tasz.institutionmanager.TestDataProvider.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDetailsConverterTest {

    private final UserDetailsConverter userDetailsConverter = new UserDetailsConverter();

    @Test
    public void convert_FullData_WithInstitution() {
        final InstitutionEntity institutionEntity = getInstitutionEntity();
        final RoleEntity roleEntity = getAdminRoleEntity();

        final UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setUsername("test_username");
        userEntity.setName("test_name");
        userEntity.setPassword("test_password");
        userEntity.setInstitutionEntity(institutionEntity);
        userEntity.setRoleEntity(roleEntity);

        final UserDetails userDetails = userDetailsConverter.convert(userEntity);

        assertThat(userDetails.getUsername(), is("test_username"));
        assertThat(userDetails.getName(), is("test_name"));
        assertThat(userDetails.getInstitution(), is("test_institution"));
        assertThat(userDetails.getRole(), is(Role.ADMIN));
    }

    @Test
    public void convert_FullData_WithoutInstitution() {
        final UserEntity userEntity = getUserEntity_Admin();

        final UserDetails userDetails = userDetailsConverter.convert(userEntity);

        assertThat(userDetails.getUsername(), is("test_username"));
        assertThat(userDetails.getName(), is("test_name"));
        assertThat(userDetails.getInstitution(), nullValue());
        assertThat(userDetails.getRole(), is(Role.ADMIN));
    }

    @Test
    public void convert_Null() {
        final UserDetails userDetails = userDetailsConverter.convert(null);

        assertThat(userDetails, nullValue());
    }
}