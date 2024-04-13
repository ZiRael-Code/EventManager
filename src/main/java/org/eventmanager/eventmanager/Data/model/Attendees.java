package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class Attendees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationMade;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notificationList;
}
