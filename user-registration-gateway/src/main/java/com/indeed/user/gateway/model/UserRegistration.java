package com.indeed.user.gateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
public class UserRegistration implements Serializable {
    String username;
    String email;
    String firstname;
    String lastname;
    UUID id;

    @JsonCreator
    public UserRegistration(@JsonProperty("username") String username,
                            @JsonProperty("email") String email,
                            @JsonProperty("firstname") String firstname,
                            @JsonProperty("lastname") String lastname,
                            @JsonProperty("id") UUID id) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }
}
