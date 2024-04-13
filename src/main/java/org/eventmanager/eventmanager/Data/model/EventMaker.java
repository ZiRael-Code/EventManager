package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class EventMaker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notificationList;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;
}
