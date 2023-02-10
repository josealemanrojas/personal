package com.indeed.user.worker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
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
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@Table(schema = "users", name = "user_registration")
@Entity
public class UserRegistration implements Serializable {
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "firstname")
    String firstname;
    @Column(name = "lastname")
    String lastname;

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