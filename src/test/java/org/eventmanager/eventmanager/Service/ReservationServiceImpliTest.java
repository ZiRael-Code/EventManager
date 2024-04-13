package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.dtos.request.MakeReservationRequest;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceImpliTest {

    @Autowired
    ReservationService reservationService;
    @Test
    void makeReservation() throws UserNotFoundException, InvalidCategoryException {

        MakeReservationRequest request = new MakeReservationRequest();
        request.setEventName("Game boi");
        request.setEventDate("2024-4-12");
        request.setEmail("attendees12@gmail.com");
        request.setEventCategory("GAMING");
        assertNotNull(reservationService.makeReservation(request));
    }

    @Test
    void cancelReservation() {
    }

    @Test
    void viewReservation() {
    }

    @Test
    void viewAllReservation() {
    }
}