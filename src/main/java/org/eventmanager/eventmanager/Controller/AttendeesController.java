package org.eventmanager.eventmanager.Controller;

import org.eventmanager.eventmanager.Service.AttendeesService;
import org.eventmanager.eventmanager.Service.EventMakerService;
import org.eventmanager.eventmanager.dtos.request.CancelReservationRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.RegisterAttendeesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendee")
public class AttendeesController {
    @Autowired
    private AttendeesService attendees;


    @PostMapping("/registerAttendees")
    public ResponseEntity<RegisterAttendeesResponse> registerAttendees(@RequestBody RegisterRequest request){
        try{
           RegisterAttendeesResponse response = attendees.register(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(new RegisterAttendeesResponse(-1L, "Failed to register attendees"+e.getMessage()));
        }
    }

    @GetMapping("/viewBookedEvent/{userEmail}")
    public ResponseEntity<?> viewBookedEvent(@PathVariable("userEmail") String userEmail){
        try {
            return ResponseEntity.ok().body(attendees.viewBookedEvent(userEmail));
        }catch (Exception e){
        return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("/cancelReservation")
    public ResponseEntity<?>  cancelReservation(@RequestBody CancelReservationRequest cancelReservationRequest){
        try {
            return ResponseEntity.ok().body(attendees.cancelReservation(cancelReservationRequest));
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/checkNotification/{email}")
    public ResponseEntity<?>  checkNotification(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok().body(attendees.checkNotification(email));
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/checkNotification/{id}")
    public ResponseEntity<?>  deleteNotification(@PathVariable("id") Long id) {
        try {
            attendees.deleteNotification(id);
            return ResponseEntity.ok().body("Notification deleted successfully");
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }




}
