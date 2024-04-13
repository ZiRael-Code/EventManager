package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.User;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendeesRepo extends JpaRepository<Attendees, Long> {
    Optional<Attendees> findAttendeesByUser(User user);
}
