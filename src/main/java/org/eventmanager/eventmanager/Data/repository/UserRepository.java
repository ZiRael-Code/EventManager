package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
