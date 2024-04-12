package org.eventmanager.eventmanager.Controller;

import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.EventMaker;
import org.eventmanager.eventmanager.Service.AttendeesService;
import org.eventmanager.eventmanager.Service.EventMakerService;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.eventmanager.eventmanager.dtos.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController  {
    @Autowired
    private AttendeesService attendees;
    @Autowired
    private EventMakerService service;


    @PostMapping("/registerAttendees")
    public ResponseEntity<RegisterAttendeesResponse> registerAttendees(@RequestBody RegisterRequest request){
        try{
           RegisterAttendeesResponse response = attendees.register(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(new RegisterAttendeesResponse(-1L, "Failed to register attendees"+e.getMessage()));
        }
    }
    @PostMapping("/registerEventMaker")
    public ResponseEntity<RegisterAttendeesResponse> registerEventMaker(@RequestBody RegisterRequest request) {
        try{
            RegisterAttendeesResponse response = service.register(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(new RegisterAttendeesResponse(-1L, "Failed to register Event maker"+e.getMessage()));
        }
    }

    

    }
