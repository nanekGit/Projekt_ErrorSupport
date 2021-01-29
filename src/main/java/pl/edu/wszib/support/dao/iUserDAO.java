package pl.edu.wszib.support.dao;

import pl.edu.wszib.support.model.User;

public interface iUserDAO {

    User getUserByLogin(String login);
    boolean persistUser(User user);
}
