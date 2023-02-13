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
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Table(schema = "notifications", name = "user_notification")
@Entity
public class UserNotification {
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "notificationType")
    NotificationType notificationType;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(
            name = "id",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "UUID"
    )
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