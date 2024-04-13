package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.FoundEvent;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import java.util.List;

public interface EventService {
    CreateEventResponse createEvent(CreateEventRequest request) throws UserNotFoundException, InvalidCategoryException;
    FoundEvent findEvent(FindEventRequest findEventRequest) throws InvalidCategoryException;

    void deleteEvent(FindEventRequest findEventRequest) throws InvalidCategoryException;
    List<Event> findAllEventMakerEvent(String email) throws UserNotFoundException;
    List<Event> findAllEvent();
    List<String> findAllRegisteredTicket(FindEventRequest findEventRequest) throws UserNotFoundException, InvalidCategoryException;

    void addTicket(FindEventRequest findEventRequest, String ticketAdd) throws InvalidCategoryException;
}
