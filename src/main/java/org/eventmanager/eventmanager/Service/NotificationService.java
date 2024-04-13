package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Data.model.Notification;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import java.util.List;

public interface NotificationService {

    void sendNotificationToAllUserIfReservationDatePassWitoutUserCompleting(List<Reservation> reservation);

    void deletePassedReservationAfterSomeDuration();

    void deleteNotification(Long id);

    List<Notification> getAllNotificationBelongingTo(String email);
    Notification sendNotificationForEventCreation(Event event);
    Notification sendDeleteEventNotification(Event event);
    void checkAndSendNotificationToAttendeesForAlmostDueBookedEvent(List<Reservation> booked);
    Notification sendNotificationToAttendeesForBookedEvent(Reservation reservation) throws UserNotFoundException;
    void sendNotificationToNewUser(String email) throws UserNotFoundException;

    void sendCancelReservationNotification();

    void sendNoMoreTicketNotification(EventMaker maker);
}

