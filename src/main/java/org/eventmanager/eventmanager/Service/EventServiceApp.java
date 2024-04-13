package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Data.repository.EventRepository;
import org.eventmanager.eventmanager.Utils.RequestMapper;
import org.eventmanager.eventmanager.Utils.ResponseMapper;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.FoundEvent;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceApp implements EventService{
    RequestMapper mapper = new RequestMapper();
    ResponseMapper response = new ResponseMapper();
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    EventMakerService eventMakerService;
    @Autowired
    UserService userService;
    @Override
    public CreateEventResponse createEvent(CreateEventRequest request) throws UserNotFoundException, InvalidCategoryException {
        EventMaker user = eventMakerService.findEventMaker(request.getCreatorEmail());
        verifyCategory(request.getCategory().toUpperCase());
        Event event = mapper.map(request);
        event.setEventMakerId(user);
        Event maker = eventRepository.save(event);
        return response.map(maker);
    }

    private Category verifyCategory(String category) throws InvalidCategoryException {
        if (!Arrays.stream(Category.values())
                .anyMatch(cat -> cat.name().equals(category.toUpperCase()))) {
            throw new InvalidCategoryException("Invalid category");
        }
        return Category.valueOf(category.toUpperCase());
    }

    public FoundEvent findEvent(FindEventRequest findEventRequest) throws InvalidCategoryException {
       Category category =  verifyCategory(findEventRequest.getEventCategory());
       Optional<Event> foundEvent = eventRepository.findEventByNameAndCategoryAndEventDate(findEventRequest.
                getEventName(), category, LocalDate.parse(findEventRequest.getEventDate(), DateTimeFormatter.ofPattern("yyyy-M-d")));
    if (foundEvent.isPresent()){
        return new FoundEvent("Event found", foundEvent.get());
    }else {
        throw new RuntimeException("Event Not Found");
    }
    }

    @Override
    public void deleteEvent(FindEventRequest findEventRequest) throws InvalidCategoryException {
      Event e = findEvent(findEventRequest).getEvent();
      eventRepository.delete(e);
    }

    @Override
    public List<Event> findAllEventMakerEvent(String email) throws UserNotFoundException {
       Optional<List<Event>> events = eventRepository.findEventsByEventMakerId(eventMakerService.findEventMaker(email));
        if (events.isPresent()){
            return events.get();
        }
        throw new RuntimeException("Not fount");
    }

    @Override
    public List<Event> findAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public List<String> findAllRegisteredTicket(FindEventRequest findEventRequest) throws UserNotFoundException, InvalidCategoryException {
       Event event = findEvent(findEventRequest).getEvent();
       return event.getRegisteredTicket();
    }

@Override
    public void addTicket(FindEventRequest findEventRequest, String ticketAdd) throws InvalidCategoryException {
       Event event =  findEvent(findEventRequest).getEvent();
        List<String> ticket =  event.getRegisteredTicket();
    ticket.add(ticketAdd);
    event.setRegisteredTicket(ticket);
    eventRepository.save(event);
    }

}
