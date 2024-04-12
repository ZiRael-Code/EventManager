package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.FoundEvent;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import java.util.List;

public interface AttendeesService {
    void makeReservation();
    void viewBookedEvent();
    void cancelReservation();
    void checkNotification();
    void deleteNotification();
}
