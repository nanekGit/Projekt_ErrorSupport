package pl.edu.wszib.support.services;

import pl.edu.wszib.support.model.User;
import pl.edu.wszib.support.model.view.RegistrationModel;

import java.util.regex.Pattern;

public interface iUserService {

    Pattern getLengthPattern();
    User getUserByID(int id);
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
}
