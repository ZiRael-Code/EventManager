package org.eventmanager.eventmanager.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventRequest {
    private String email;
    private String eventName;
    private String eventCategory;
    private String eventDate;
}
