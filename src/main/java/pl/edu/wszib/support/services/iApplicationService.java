package pl.edu.wszib.support.services;

import pl.edu.wszib.support.model.Application;

import java.util.List;
import java.util.regex.Pattern;

public interface iApplicationService {

    Pattern getPatternForLength(int length);
    List<Application> getAllApplications();
    Application getApplicationByID(int id);
    Application getApplicationByName(String name);
    int addApplication(Application app);
}
