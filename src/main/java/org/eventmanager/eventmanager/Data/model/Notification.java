package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String email;
    private String Title;
    private String description;
    private LocalDate localDate;
    @ManyToOne
    private Attendees attendees;

    public Notification() {
        localDate = LocalDate.now();
    }
}
