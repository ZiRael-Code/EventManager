package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.Data.repository.UserRepository;
import org.eventmanager.eventmanager.Utils.RequestMapper;
import org.eventmanager.eventmanager.Utils.ResponseMapper;
import org.eventmanager.eventmanager.Utils.Validate;
import org.eventmanager.eventmanager.dtos.request.FindEventRequest;
import org.eventmanager.eventmanager.dtos.request.MakeReservationRequest;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.dtos.response.FindAllMyEvent;
import org.eventmanager.eventmanager.dtos.response.FoundEvent;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.dtos.response.RegisterResponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceApp implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    EventService eventService;
    RequestMapper requestMapper = new RequestMapper();
    ResponseMapper responseMapper = new ResponseMapper();
    @Override
    public RegisterResponse registerUser(RegisterRequest request) throws InvalidNameException, InvalidPasswordException, InvalidEmailException, UserNotFoundException {
        Validate.validate(request.getName(), request.getEmail(), request.getPassword());
        User user = userRepository.save(requestMapper.map(request));
        notificationService.sendNotificationToNewUser(request.getEmail());
        return responseMapper.map(user);
    }

    @Override
    public User findByEmail(String creatorEmail) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(creatorEmail);
        if (user.isPresent()){
            return user.get();
        }else throw new UserNotFoundException("User not Found");
    }


    public void validateLogin(String email) throws UserNotFoundException {
        if (!findByEmail(email).isLoginStatus()){
            throw new RuntimeException("User not logged in");
        }
    }
}
