package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Notification;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.dtos.request.*;
import org.eventmanager.eventmanager.dtos.response.GeneralResponse;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import javax.naming.InvalidNameException;
import java.util.List;

public interface AttendeesService {
//    void makeReservation();

    MakeReservationRsponse makeReservation(MakeReservationRequest request) throws UserNotFoundException, InvalidCategoryException;

    List<Reservation> viewBookedEvent(String userEmail) throws UserNotFoundException;


    GeneralResponse cancelReservation(CancelReservationRequest cancelReservationRequest) throws InvalidCategoryException, UserNotFoundException;

    List<Notification> checkNotification(String email) throws UserNotFoundException;
    RegisterAttendeesResponse register(RegisterRequest request) throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException ;

    void deleteNotification(Long id);
    Attendees findAttendees(String email) throws UserNotFoundException;
}
