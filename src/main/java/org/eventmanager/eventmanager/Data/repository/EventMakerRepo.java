package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeesRepo extends JpaRepository<Attendees, Long> {
}
