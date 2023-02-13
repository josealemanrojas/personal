package com.indeed.user.worker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
public class UserNotification {
    String username;
    String email;
    NotificationType notificationType;
    UUID id;

    @JsonCreator
    public UserNotification(@JsonProperty("username") String username,
                            @JsonProperty("email") String email,
                            @JsonProperty("notificationType") NotificationType notificationType,
                            @JsonProperty("id") UUID id) {
        this.username = username;
        this.email = email;
        this.notificationType = notificationType;
        this.id = id;
    }
}