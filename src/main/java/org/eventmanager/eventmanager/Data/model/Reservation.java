package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String ticket;
    String eventName;
    private LocalDate eventDate;
    @ManyToOne
    private Attendees attendeeId;

    private String description;
    @Enumerated
    private Category category;
    private boolean complete;

}
