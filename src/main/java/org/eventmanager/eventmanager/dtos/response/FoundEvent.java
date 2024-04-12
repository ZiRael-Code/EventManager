package org.eventmanager.eventmanager.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.User;

@Getter
@Setter
public class CreateEventResponse {
    private Event event;
    private String data;
}
