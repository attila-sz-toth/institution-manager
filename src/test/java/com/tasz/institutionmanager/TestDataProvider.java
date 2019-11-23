package com.tasz.institutionmanager;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.constants.InstitutionType;
import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestDataProvider {
    public static InstitutionEntity getInstitutionEntity() {
        InstitutionCareTypeEntity institutionCareTypeEntity_1 = new InstitutionCareTypeEntity();
        institutionCareTypeEntity_1.setId(0);
        institutionCareTypeEntity_1.setCareType("IDOSEK_OTTHONA");

        InstitutionCareTypeEntity institutionCareTypeEntity_2 = new InstitutionCareTypeEntity();
        institutionCareTypeEntity_2.setId(1);
        institutionCareTypeEntity_2.setCareType("HAZI_SEGITSEG_NYUJTAS");

        Set<InstitutionCareTypeEntity> careTypes = new HashSet<>();
        careTypes.add(institutionCareTypeEntity_1);
        careTypes.add(institutionCareTypeEntity_2);

        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setId(0);
        institutionEntity.setName("test_institution");
        institutionEntity.setAddress("test_address");
        institutionEntity.setType(InstitutionType.IDOSEK_OTTHONA);
        institutionEntity.setCareTypes(careTypes);

        return institutionEntity;
    }

    public static RoleEntity getAdminRoleEntity() {
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(0);
        roleEntity.setRoleName("ADMIN");

        return roleEntity;
    }

    public static RoleEntity getEmployeeRoleEntity() {
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setRoleName("EMPLOYEE");

        return roleEntity;
    }

    public static UserDetails getUserDetails_Admin() {
        final UserDetails userDetails = new UserDetails();
        userDetails.setUsername("test_username");
        userDetails.setName("test_name");
        userDetails.setRole(Role.ADMIN);

        return userDetails;
    }

    public static UserDetails getUserDetails_Employee() {
        final UserDetails userDetails = new UserDetails();
        userDetails.setUsername("test_username");
        userDetails.setName("test_name");
        userDetails.setRole(Role.EMPLOYEE);
        userDetails.setInstitution("test_institution");

        return userDetails;
    }

    public static UserEntity getUserEntity_Admin() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setUsername("test_username");
        userEntity.setName("test_name");
        userEntity.setPassword("test_password");
        userEntity.setRoleEntity(getAdminRoleEntity());

        return userEntity;
    }

    public static UserEntity getUserEntity_Employee() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setUsername("test_username");
        userEntity.setName("test_name");
        userEntity.setPassword("test_password");
        userEntity.setRoleEntity(getEmployeeRoleEntity());
        userEntity.setInstitutionEntity(getInstitutionEntity());

        return userEntity;
    }

    public static CareReceiverEntity getCareReceiverEntity(final Date now) {
        final CareReceiverEntity careReceiverEntity = new CareReceiverEntity();
        careReceiverEntity.setAddress("test_address");
        careReceiverEntity.setBirthDate(now);
        careReceiverEntity.setBirthName("birth_name");
        careReceiverEntity.setBirthPlace("birth_place");
        careReceiverEntity.setCareStatus(CareStatus.ACTIVE);
        careReceiverEntity.setEmail("e_mail");
        careReceiverEntity.setEndOfCare(now);
        careReceiverEntity.setFirstName("first_name");
        careReceiverEntity.setId(0);
        careReceiverEntity.setInstitutionEntity(getInstitutionEntity());
        careReceiverEntity.setLastName("last_name");
        careReceiverEntity.setMothersName("mothers_name");
        careReceiverEntity.setPhoneNumber("phone_number");
        careReceiverEntity.setSex(Sex.FERFI);
        careReceiverEntity.setStartOfCare(now);

        return careReceiverEntity;
    }
}
