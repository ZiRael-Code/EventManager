package org.eventmanager.eventmanager.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.eventmanager.eventmanager.Data.model.Event;

@Getter
@Setter
public class FoundEvent {
    private Event event;
    private String data;
    private Long availableAttendees;

    public FoundEvent(String eventFound, Event event) {
        this.data = eventFound;
        this.event = event;

    }
}
