package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByNameAndCategoryAndEventDate(String name, Category category, LocalDate eventDate);
    Optional<List<Event>> findEventsByEventMakerId(EventMaker user);

}
