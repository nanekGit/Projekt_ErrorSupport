package pl.edu.wszib.support.dao;

import pl.edu.wszib.support.model.Application;

import java.util.List;

public interface iApplicationDAO {

    List<Application> getAllApplications();
    Application getApplicationByID(int id);
    Application getApplicationByName(String name);
    boolean persistApplication(Application app);
}
