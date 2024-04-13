package org.eventmanager.eventmanager.dtos.response;

import lombok.Data;
import org.eventmanager.eventmanager.Data.model.Event;

import java.util.List;
@Data
public class GetAllEventResponse {
    List<Event> eventMade;
    String data;

    public GetAllEventResponse(List<Event> eventMade, String data) {
        this.eventMade = eventMade;
        this.data = data;
    }
}
