package com.tasz.institutionmanager.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.tasz.institutionmanager.constants.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    private String username;
    private Role role;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
    }
}
