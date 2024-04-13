package org.eventmanager.eventmanager.dtos.response;

import lombok.Data;
import org.springframework.web.servlet.tags.EditorAwareTag;
@Data
public class RegisterAttendeesResponse {
    private Long id;
    private String data;

    public RegisterAttendeesResponse(Long id, String attendeesRegisteredSuccess) {
        this.id = id;
        this.data = attendeesRegisteredSuccess;
    }
}
