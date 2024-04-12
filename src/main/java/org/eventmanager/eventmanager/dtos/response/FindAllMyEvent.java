package org.eventmanager.eventmanager.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.eventmanager.eventmanager.Data.model.Event;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class FindAllEvent {
    List<Event> eventList;

}
