package org.eventmanager.eventmanager.Utils;

import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
public class Mapper {

    public User map(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
