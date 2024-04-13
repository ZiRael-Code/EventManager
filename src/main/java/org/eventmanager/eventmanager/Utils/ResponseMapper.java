package org.eventmanager.eventmanager.Utils;

import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.dtos.response.CreateEventResponse;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.dtos.response.RegisterResponse;

import java.util.List;

public class ResponseMapper {

    public RegisterResponse map(User user){
        RegisterResponse response = new RegisterResponse();
        response.setData("Registered successful");
        response.setUser(user);
        return response;
    }

    public CreateEventResponse map(Event event) {
        CreateEventResponse createEventResponse = new CreateEventResponse();
        createEventResponse.setData("Response created successful");
        createEventResponse.setEvent(event);
        return createEventResponse;
    }

    public MakeReservationRsponse reservationMap(Reservation reservation, List<Reservation> getReservationList) {
        MakeReservationRsponse response = new MakeReservationRsponse();
        response.setEventName(reservation.getEventName());
        response.setDescription(reservation.getDescription());
        response.setCategory(String.valueOf(reservation.getCategory()));
        response.setTicket(reservation.getTicket());
        response.setReservationList(getReservationList);
        response.setEventDate(String.valueOf(reservation.getEventDate()));
        return response;
    }
}
