package pl.edu.wszib.support.services;

import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.model.Error;

import java.util.List;
import java.util.regex.Pattern;

public interface iErrorService {

    Pattern getPatternForLength(int length);
    List<Error> getAllErrors();
    List<Error> getAllErrorsForApplication(Application app);
    Error getErrorByID(int id);
    boolean addError(Error error);
    boolean updateErrorState(Error error);
}
