package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.dtos.request.CancelReservationRequest;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.InvalidNameException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventMakerServiceImplTest {

    @Autowired EventMakerService eventMakerService;

    @Test
    void deleteEvent() throws UserNotFoundException, InvalidCategoryException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        makeEvent();
        FindEventRequest findEventRequest = new FindEventRequest();
        findEventRequest.setEmail("eventMaker12@gmal.com");
        findEventRequest.setEventCategory(String.valueOf(Category.GAMING));
        findEventRequest.setEventName("Game boi");
        findEventRequest.setEventDate("2024-4-12");

        eventMakerService.deleteEvent(findEventRequest);
        assertEquals(0, eventMakerService.getAllEventMade("eventMaker12@gmal.com").getEventMade().size());
    }

    @Test
    void getAllEventMade() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        makeEvent();
        assertEquals(1, eventMakerService.getAllEventMade("eventMaker12@gmal.com").getEventMade().size());

    }

    @Test
    void register() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        RegisterRequest eventMakerRequest = new RegisterRequest();
        eventMakerRequest.setName("name");
        eventMakerRequest.setPassword("Israelites12!");
        eventMakerRequest.setEmail("eventMaker12@gmal.com");
        RegisterAttendeesResponse response = eventMakerService.register(eventMakerRequest);
        assertNotNull(response.getId());
    }

    @Test
    void makeEvent() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        register();
        CreateEventRequest createEvent = new CreateEventRequest();
        createEvent.setCreatorEmail("eventMaker12@gmal.com");
        createEvent.setDescription("testing create event");
        createEvent.setCategory("GAMING");
        createEvent.setAvailableAttendees(500L);
        createEvent.setAvailableTicket(100L);
        createEvent.setName("Game boi");
        createEvent.setDate("2024-4-12");
        Event event = eventMakerService.makeEvent(createEvent).getEvent();
        assertNotNull(event);
    }
}