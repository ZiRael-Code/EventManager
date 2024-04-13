package org.eventmanager.eventmanager.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.SecondaryRow;

@Setter
@Getter

public class CreateEventRequest {
    private String creatorEmail;
    private String name;
    private String date;
    private Long availableAttendees;
    private String description;
    private String category;
    private Long availableTicket;

}
