package pl.edu.wszib.support.dao;

import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.model.Error;

import java.util.List;

public interface iErrorDAO {

    List<Error> getAllErrors();
    List<Error> getAllErrorsForApplication(Application app);
    Error getErrorByID(int id);
    boolean persistError(Error error);
    boolean updateError(Error error);
}
