package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Data.model.Notification;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.GetAllEventResponse;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import javax.naming.InvalidNameException;
import java.util.List;

public interface EventMakerService {
    Long getRemainingTicket(FindEventRequest findEventRequest) throws InvalidCategoryException, UserNotFoundException;
    List<Notification> checkNotification(Long id, String email) throws UserNotFoundException ;
        void deleteNotification(Long id, String email) throws UserNotFoundException ;
    void deleteEvent(FindEventRequest findEventRequest) throws InvalidCategoryException, UserNotFoundException;
    boolean verifyAttendeesTicket(FindEventRequest findEventRequest, String ticket) throws UserNotFoundException, InvalidCategoryException ;
    GetAllEventResponse getAllEventMade(String email) throws UserNotFoundException;
    EventMaker findEventMaker(String email) throws UserNotFoundException;

    RegisterAttendeesResponse register(RegisterRequest request) throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException;
    CreateEventResponse makeEvent(CreateEventRequest makeEventRequest) throws UserNotFoundException, InvalidCategoryException;
}
