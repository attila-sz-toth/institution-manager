package com.tasz.institutionmanager.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = UserRegistrationDetails.UserRegistrationDetailsBuilder.class)
public class UserRegistrationDetails {
    private String username;
    private String password;
    private String role;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserRegistrationDetailsBuilder {
    }
}
