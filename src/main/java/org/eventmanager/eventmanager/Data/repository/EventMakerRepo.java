package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventMakerRepo extends JpaRepository<EventMaker, Long> {
    Optional<EventMaker> findEventMakerByUser(User user);

}
