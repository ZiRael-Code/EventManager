package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.dtos.request.*;
import org.eventmanager.eventmanager.dtos.response.GeneralResponse;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.dtos.response.RegisterResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.naming.InvalidNameException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AttendeesServiceImplTest {
    @Autowired
    UserService userService;
    @Autowired
    AttendeesService attendeesService;
    @Autowired
    EventService eventService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    EventMakerService eventMakerService;

    UserServiceAppTest userServiceAppTest;

    @BeforeEach
    void text(){
        userServiceAppTest = new UserServiceAppTest();
    }


    @Test
//    @Sql(scripts = {"/script/insert.sql"})
    void makeReservationText() throws UserNotFoundException, InvalidCategoryException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        helperAttendeesRegister();
       helperEventMakerRegister();
        helperEventCreate();

        MakeReservationRequest request = new MakeReservationRequest();
        request.setEventName("Game boi");
        request.setEventDate("2024-4-12");
        request.setEmail("attendees12@gmail.com");
        request.setEventCategory("GAMING");

        assertNotNull(attendeesService.makeReservation(request));
        assertEquals(1, attendeesService.viewBookedEvent("attendees12@gmail.com").size());
    }

    @Test
    void viewBookedEvent() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        helperAttendeesRegister();
        assertEquals(0, attendeesService.viewBookedEvent("attendees12@gmail.com").size());
    }

    @Test
    void cancelReservation() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        helperEventMakerRegister();
        helperEventCreate();

        helperAttendeesRegister();
        helperMakeReservation();

        assertEquals(1, attendeesService.viewBookedEvent("attendees12@gmail.com").size());
        CancelReservationRequest request = new CancelReservationRequest();
        request.setName("Game boi");
        request.setCategory(Category.GAMING);
        request.setEmail("attendees12@gmail.com");
        GeneralResponse generalResponse = attendeesService.cancelReservation(request);
                assertNotNull(generalResponse);
        assertEquals(0, attendeesService.viewBookedEvent("attendees12@gmail.com").size());
//
    }

    @Test
    void testCreateEventTest() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        helperEventMakerRegister();
       helperEventCreate();
       helperAttendeesRegister();

        MakeReservationRequest request = new MakeReservationRequest();
        request.setEventName("Game boi");
        request.setEventDate("2024-4-12");
        request.setEmail("attendees12@gmail.com");
        request.setEventCategory("GAMING");

        System.out.println(attendeesService.makeReservation(request).toString());
    }


    @Test
    void testThatRegisteredNotificationIsSentAfterUserRegistration() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        helperAttendeesRegister();
        assertEquals(1, notificationService.getAllNotificationBelongingTo("attendees12@gmail.com").size());
    }

    @Test
     void helperAttendeesRegister() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        RegisterRequest attendees = new RegisterRequest();
        attendees.setName("name");
        attendees.setPassword("Israelites12!");
        attendees.setEmail("attendees12@gmail.com");
        RegisterAttendeesResponse attendees1 = attendeesService.register(attendees);
        assertNotNull(attendees1.getId());
    }

    @Test
    void helperEventMakerRegister() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException {
        RegisterRequest eventMakerRequest = new RegisterRequest();
        eventMakerRequest.setName("name");
        eventMakerRequest.setPassword("Israelites12!");
        eventMakerRequest.setEmail("eventMaker12@gmal.com");
        RegisterAttendeesResponse response = eventMakerService.register(eventMakerRequest);
        assertNotNull(response.getId());
    }

    @Test
    void helperEventCreate() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {

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

    @Test
    void helperMakeReservation() throws UserNotFoundException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidCategoryException {
        MakeReservationRequest request = new MakeReservationRequest();
        request.setEventName("Game boi");
        request.setEventDate("2024-4-12");
        request.setEmail("attendees12@gmail.com");
        request.setEventCategory("GAMING");

        assertNotNull(attendeesService.makeReservation(request));

    }
    }