package org.eventmanager.eventmanager.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.User;

import java.util.List;

@Getter
@Setter
public class RegisterResponse {
    private User user;
    private String data;
}
