package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.*;
import org.eventmanager.eventmanager.Data.repository.EventMakerRepo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Optional;

@Service
public class EventMakerServiceImpl implements EventMakerService{
    @Autowired
    private UserService userService;
    @Autowired
    private EventMakerRepo eventMakerRepo;
    @Autowired
    private EventService eventService;
    @Autowired
    private NotificationService notificationService;


    @Override
    public Long getRemainingTicket(FindEventRequest findEventRequest) throws InvalidCategoryException, UserNotFoundException {
        userService.validateLogin(findEventRequest.getEmail());
       Event event = eventService.findEvent(findEventRequest).getEvent();
       return event.getAvailableTicket();
    }

    @Override
    public List<Notification> checkNotification(Long id, String email) throws UserNotFoundException {
        userService.validateLogin(email);
        notificationService.sendNoMoreTicketNotification(findEventMaker(email));
        return notificationService.getAllNotificationBelongingTo(email);
    }

    @Override
    public void deleteNotification(Long id, String email) throws UserNotFoundException {
        userService.validateLogin(email);

        notificationService.deleteNotification(id);
    }

    @Override
    public void deleteEvent(FindEventRequest findEventRequest) throws InvalidCategoryException, UserNotFoundException {
//       userService.validateLogin(findEventRequest.getEmail());
        notificationService.sendDeleteEventNotification(eventService.findEvent(findEventRequest).getEvent());
        eventService.deleteEvent(findEventRequest);
    }

    @Override
    public boolean verifyAttendeesTicket(FindEventRequest findEventRequest, String ticket) throws UserNotFoundException, InvalidCategoryException {
       userService.validateLogin(findEventRequest.getEmail());
      if (eventService.findAllRegisteredTicket(findEventRequest)
               .stream().anyMatch(tic -> tic.equals(ticket))){
          return true;
      }
      throw new RuntimeException("Ticket is not valid");
    }

    @Override
    public GetAllEventResponse getAllEventMade(String email) throws UserNotFoundException {
//        userService.validateLogin(email);
       return new GetAllEventResponse(eventService.findAllEventMakerEvent(email), "Success");
    }

    @Override
    public EventMaker findEventMaker(String email) throws UserNotFoundException {
        Optional<EventMaker> eventMaker = eventMakerRepo.findEventMakerByUser(userService.findByEmail(email));
        if (eventMaker.isPresent()) {
            EventMaker maker = eventMaker.get();
            notificationService.sendNoMoreTicketNotification(maker);
            return maker;
        }else {
            throw new RuntimeException("Event maker not found with email "+email);
        }
    }

    @Override
    public RegisterAttendeesResponse register(RegisterRequest request) throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        User user = userService.registerUser(request).getUser();
        EventMaker eventMaker = new EventMaker();
        eventMaker.setUser(user);
        eventMakerRepo.save(eventMaker);
        return new RegisterAttendeesResponse(user.getId(), "event maker registered success");
    }

    public CreateEventResponse makeEvent(CreateEventRequest makeEventRequest) throws UserNotFoundException, InvalidCategoryException {
//        userService.validateLogin(makeEventRequest.getCreatorEmail());
        CreateEventResponse response = eventService.createEvent(makeEventRequest);
        notificationService.sendNotificationForEventCreation(response.getEvent());
        return response;

    }
}
