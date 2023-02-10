package com.indeed.user.worker.repository;

import com.indeed.user.worker.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, UUID> {
    boolean existsByEmail(String email);
}
