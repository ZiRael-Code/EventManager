package org.eventmanager.eventmanager.Utils;

import org.eventmanager.eventmanager.exception.InvalidEmailException;
import org.eventmanager.eventmanager.exception.InvalidPasswordException;
import org.hibernate.mapping.List;

import javax.naming.InvalidNameException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Validate {
    public static void emailValidate(String email) throws InvalidEmailException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!pattern.matcher(email).matches()){
            throw new InvalidEmailException("Please input the right email format");
        }
    }

    public static void passwordValidate(String password) throws InvalidPasswordException {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        if (!pattern.matcher(password).matches()){
            throw new InvalidPasswordException("Please input the right password format");
        }
    }

    public static void nameValidate(String name) throws InvalidNameException {
        if (name.length() > 100) throw new InvalidNameException("Please input a valid name format");
    }

    public static void validate(String name, String email, String password) throws InvalidNameException, InvalidPasswordException, InvalidEmailException {
        nameValidate(name);
        emailValidate(email);
        passwordValidate(password);

    }


}
