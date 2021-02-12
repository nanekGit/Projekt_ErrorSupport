package pl.edu.wszib.support.dao;

import pl.edu.wszib.support.model.User;

public interface iUserDAO {

    User getUserByLogin(String login);
    User getUserByID(int id);
    boolean persistUser(User user);
}
