package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate eventDate;
    private LocalDate dateCreated;
    private Long availableAttendees;
    private String description;

    @Enumerated
    private Category category;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.DETACH})
    private EventMaker eventMakerId;
    private Long availableTicket;

    @Fetch(FetchMode.SELECT)
    @ElementCollection()
    @Transient
    private List<String> registeredTicket = new ArrayList<>();
}
