package org.eventmanager.eventmanager.Controller;

import org.eventmanager.eventmanager.Service.EventMakerService;
import org.eventmanager.eventmanager.dtos.request.CreateEventRequest;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.GetAllEventResponse;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

@RestController
@RequestMapping("/eventMaker")
public class Event_MakerController {
    @Autowired
    private EventMakerService service;


    @PostMapping("/registerEventMaker")
    public ResponseEntity<RegisterAttendeesResponse> registerEventMaker(@RequestBody RegisterRequest request) {
        try{
            RegisterAttendeesResponse response = service.register(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(new RegisterAttendeesResponse(-1L, "Failed to register Event maker"+e.getMessage()));
        }
    }

    @PostMapping("/createEvent")
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest createEventRequest){
        try{
            CreateEventResponse response = service.makeEvent(createEventRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
           CreateEventResponse request = new CreateEventResponse();
           request.setData("Failed "+e.getMessage());
            return new ResponseEntity<>(request, HttpStatus.CREATED);
        }
    }
    @GetMapping("/getEventMade/{email}")
    public ResponseEntity<?> getAllEventMade(@PathVariable("email") String email){
        try {
            GetAllEventResponse response = service.getAllEventMade(email);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Failed "+e.getMessage());

        }
    }
    @GetMapping("/checkNotification/{id}/{email}")
    public ResponseEntity<?> checkNotification(@PathVariable("id") int id, @PathVariable("email") String email){
        try {
           return ResponseEntity.ok().body(service.checkNotification((long) id, email));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Failed "+e.getMessage());
        }
    }

    @GetMapping("/deleteNotification/{id}/{email}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id")Long id, @PathVariable("email")String email){
        try {
            service.deleteNotification(id, email);
            return ResponseEntity.ok().body("Notification deleted");
        }catch (Exception e){
            return ResponseEntity.ok().body("failled to deleted notification " + e.getMessage());
        }

    }

    @GetMapping("/verifyAttendeesTicket/{ticket}")
    public ResponseEntity<String> verifyAttendeesTicket(@RequestBody FindEventRequest findEventRequest, @PathVariable("ticket") String ticket){
        try {
            return service.verifyAttendeesTicket(findEventRequest, ticket)
                    ? ResponseEntity.ok().body("Attendees is in the event")
                    : ResponseEntity.badRequest().body("Attendees is not in the event");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getRemaining")
    public Long getRemainingTicket(@RequestBody FindEventRequest findEventRequest){
        try {
            return service.getRemainingTicket(findEventRequest);
        }catch (Exception e){
           return (long) HttpStatus.CREATED.value();
        }
    }


}
