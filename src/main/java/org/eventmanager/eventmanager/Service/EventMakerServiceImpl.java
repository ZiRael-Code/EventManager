package org.eventmanager.eventmanager.Service;

public interface EventMakerService {
    void makeReservation();
    void viewBookedEvent();
    void cancelReservation();
    void checkNotification();
    void deleteNotification();
}
