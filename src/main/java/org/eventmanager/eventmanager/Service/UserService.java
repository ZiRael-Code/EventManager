package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.RegisterResponse;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;

import javax.naming.InvalidNameException;

public interface UserService {
    RegisterResponse registerUser(RegisterRequest request) throws InvalidNameException, InvalidPasswordException, InvalidEmailException, UserNotFoundException;

    User findByEmail(String creatorEmail) throws UserNotFoundException;
    void validateLogin(String email) throws UserNotFoundException;
}
