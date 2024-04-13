package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.dtos.request.*;
import org.eventmanager.eventmanager.dtos.response.GeneralResponse;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import java.util.List;

public interface ReservationService {
    MakeReservationRsponse makeReservation(MakeReservationRequest makeReservationRequest) throws InvalidCategoryException, UserNotFoundException;
    GeneralResponse cancelReservation(CancelReservationRequest request) throws InvalidCategoryException, UserNotFoundException;
    Reservation viewReservation(FindReservationRequest findReservationRequest) throws UserNotFoundException;


    List<Reservation> viewAllReservation(Attendees attendees);

    List<Reservation> findAll();
}
