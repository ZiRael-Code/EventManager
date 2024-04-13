package org.eventmanager.eventmanager.dtos.response;

import lombok.Data;
import org.eventmanager.eventmanager.Data.model.Reservation;

import java.util.List;

@Data
public class MakeReservationRsponse {
    private String ticket;
    private String eventDate;
    private String eventName;
    private String description;
    private String category;
    private List<Reservation> reservationList
;}
