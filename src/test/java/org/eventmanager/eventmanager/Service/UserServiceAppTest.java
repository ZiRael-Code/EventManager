package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.User;
import org.eventmanager.eventmanager.Utils.RequestMapper;
import org.eventmanager.eventmanager.dtos.request.RegisterRequest;
import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.naming.InvalidNameException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceAppTest {
    @Autowired
    private UserService userService;
    private RequestMapper requestMapper;
    @Test
    @Sql(scripts = {"/script/insert.sql"})
    public void register__And__loginUserTest() throws InvalidNameException, InvalidPasswordException, InvalidEmailException, UserNotFoundException {
        RegisterRequest request = new RegisterRequest();
        request.setName("name");
        request.setPassword("Israelites12!");
        request.setEmail("email1@gmal.com");
        assertNotNull(userService.registerUser(request));
    }

    @Test
    @Sql(scripts = {"/script/insert.sql"})
    void findUserByEmail() throws UserNotFoundException {
       User user =  userService.findByEmail("email1@gmal.com");
       assertNotNull(user);
    }






}