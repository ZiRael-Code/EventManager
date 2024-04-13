package org.eventmanager.eventmanager.Data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean loginStatus;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Notification> notificationList;

}
