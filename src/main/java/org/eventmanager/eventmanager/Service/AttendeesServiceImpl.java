package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Notification;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.Data.repository.AttendeesRepo;
import org.eventmanager.eventmanager.dtos.request.CancelReservationRequest;
import org.eventmanager.eventmanager.dtos.request.MakeReservationRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.GeneralResponse;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeesServiceImpl  implements AttendeesService {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    AttendeesRepo attendeesRepo;
    @Override
    public MakeReservationRsponse makeReservation(MakeReservationRequest request) throws UserNotFoundException, InvalidCategoryException {
//        userService.validateLogin(request.getEmail());
       MakeReservationRsponse reservation = reservationService.makeReservation(request);
       Attendees attendees = findAttendees(request.getEmail());
       attendees.setReservationMade(reservation.getReservationList());
       attendeesRepo.save(attendees);
        return reservation;
    }
    @Override
    public List<Reservation> viewBookedEvent(String userEmail) throws UserNotFoundException {
        return reservationService.viewAllReservation(findAttendees(userEmail));

    }

    @Override
    public GeneralResponse cancelReservation(CancelReservationRequest cancelReservationRequest) throws InvalidCategoryException, UserNotFoundException {
//        viewBookedEvent(cancelReservationRequest.getEmail());
        return reservationService.cancelReservation(cancelReservationRequest);
    }

    @Override
    public List<Notification> checkNotification(String email) throws UserNotFoundException {
        userService.validateLogin(email);
       return notificationService.getAllNotificationBelongingTo(email);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationService.deleteNotification(id);
    }

    public RegisterAttendeesResponse register(RegisterRequest request) throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        User user = userService.registerUser(request).getUser();
        Attendees attendees = new Attendees();
        attendees.setUser(user);
        attendeesRepo.save(attendees);
        return new RegisterAttendeesResponse(attendees.getId(), "Attendees registered success");
    }

    public Attendees findAttendees(String email) throws UserNotFoundException {
        Optional<Attendees> attendees = attendeesRepo.findAttendeesByUser(userService.findByEmail(email));
      if (attendees.isPresent()) {
          Attendees attendees1 = attendees.get();
          List<Reservation> made = attendees1.getReservationMade();
            notificationService.checkAndSendNotificationToAttendeesForAlmostDueBookedEvent(made);
            notificationService.sendNotificationToAllUserIfReservationDatePassWitoutUserCompleting(made);
            return attendees1;
        }else {
            throw new RuntimeException("Attendees not found with email "+email);
        }
    }

}
