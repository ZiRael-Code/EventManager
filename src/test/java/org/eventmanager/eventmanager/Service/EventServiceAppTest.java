package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.naming.InvalidNameException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceAppTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventMakerService eventMakerService;
    @Test
//    @Sql(scripts = {"/script/insert.sql"})
    public void createEventTest() throws UserNotFoundException, InvalidCategoryException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        RegisterRequest request1 = new RegisterRequest();
        request1.setName("name");
        request1.setPassword("Israelites12!");
        request1.setEmail("email1@gmal.com");
       eventMakerService.register(request1);

       CreateEventRequest request = new CreateEventRequest();
        request.setCreatorEmail("email1@gmal.com");
        request.setDescription("testing create event");
        request.setCategory("GAMING");
        request.setAvailableAttendees(500L);
        request.setAvailableTicket(100L);
        request.setName("Game boi");
        request.setDate("2024-4-12");

        assertNotNull(eventService.createEvent(request));

    }

    @Test
//    @Sql(scripts = {"/script/insert.sql"})
    public void foundEventTest() throws InvalidCategoryException, UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        createEventTest();
        FindEventRequest findEventRequest = new FindEventRequest();
        findEventRequest.setEventName("Game boi");
        findEventRequest.setEventCategory("GAMING");
        findEventRequest.setEventDate("2024-4-12");
        assertNotNull(eventService.findEvent(findEventRequest).getEvent());
    }

    @Test
    @Sql(scripts = {"/script/insert.sql"})
    void deleteEvent() throws InvalidCategoryException {
        FindEventRequest findEventRequest = new FindEventRequest();
        findEventRequest.setEventName("Game boi");
        findEventRequest.setEventCategory("GAMING");
        findEventRequest.setEventDate("2024-4-10");
        eventService.deleteEvent(findEventRequest);
    }

    @Test
    @Sql(scripts = {"/script/insert.sql"})
    public void findAllEvent() throws UserNotFoundException, InvalidCategoryException {
        CreateEventRequest request = new CreateEventRequest();
        request.setCreatorEmail("email1@gmal.com");
        request.setDescription("testing create event");
        request.setCategory("GAMING");
        request.setAvailableAttendees(500L);
        request.setAvailableTicket(100L);
        request.setName("Game boi");
        request.setDate("2024-4-12");
        eventService.createEvent(request);

        assertEquals(1, eventService.findAllEventMakerEvent("email1@gmal.com").size());

    }

    @Test
    public void findAllRegisteredTicket() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        createEventTest();
        FindEventRequest findEventRequest = new FindEventRequest();
        findEventRequest.setEventName("Game boi");
        findEventRequest.setEventCategory("GAMING");
        findEventRequest.setEventDate("2024-4-12");
        List<String> registeredTicket = eventService.findEvent(findEventRequest).getEvent().getRegisteredTicket();
        assertEquals(0, registeredTicket.size());
    }

    @Test
    public void addTicketTest() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        createEventTest();
        FindEventRequest findEventRequest = new FindEventRequest();
        findEventRequest.setEventName("Game boi");
        findEventRequest.setEventCategory("GAMING");
        findEventRequest.setEventDate("2024-4-12");
        eventService.addTicket(findEventRequest, "ticket1242");
        assertNotNull(eventService.findAllRegisteredTicket(findEventRequest));
    }
}