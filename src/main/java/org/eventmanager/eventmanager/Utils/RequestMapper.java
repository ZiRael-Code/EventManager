package org.eventmanager.eventmanager.Utils;

import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestMapper {

    public User map(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public Event map(CreateEventRequest request){
        Event event = new Event();
        event.setDescription(request.getDescription());
        event.setCategory(Category.valueOf(request.getCategory().toUpperCase()));
        event.setAvailableAttendees(500L);
        event.setAvailableTicket(100L);
        event.setName(request.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        event.setEventDate(LocalDate.parse(request.getDate(), formatter));
        event.setDateCreated(LocalDate.now());

        return event;
    }
}
