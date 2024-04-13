package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.Data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findAllByAttendeeId(Attendees user);
    Optional<Reservation> findReservationByEventNameAndCategoryAndAttendeeId(String name, Category category, Attendees attendees);


    Optional<Reservation> findReservationByEventNameAndCategory(String name, Category category);
}
