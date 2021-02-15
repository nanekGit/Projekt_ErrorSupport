package pl.edu.wszib.support.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.support.dao.iUserDAO;
import pl.edu.wszib.support.model.User;
import pl.edu.wszib.support.model.view.RegistrationModel;
import pl.edu.wszib.support.services.iUserService;
import pl.edu.wszib.support.session.SessionObject;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements iUserService {

    private final Pattern lengthPattern = Pattern.compile("[\\p{L}\\p{M}0-9_.-]{5}.*");

    @Autowired
    iUserDAO userDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public Pattern getLengthPattern() {
        return lengthPattern;
    }

    @Override
    public User getUserByID(int id) {
        return this.userDAO.getUserByID(id);
    }

    @Override
    public void authenticate(User user) {
        User userFromDB = userDAO.getUserByLogin(user.getLogin());
        if(userFromDB!=null && userFromDB.getPass().equals(user.getPass())) {
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public int register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null){
            return 1;
        }
        User newUser = new User(registrationModel.getLogin(),registrationModel.getPass(), User.Role.USER);
        if(this.userDAO.persistUser(newUser)){
            return 0;
        }
        return 2;
    }
}
