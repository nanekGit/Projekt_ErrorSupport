package pl.edu.wszib.support.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.support.dao.iErrorDAO;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.model.Error;
import pl.edu.wszib.support.services.iErrorService;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ErrorServiceImpl implements iErrorService {

    private final Pattern lengthPattern5 = Pattern.compile("[A-Za-z0-9._-]{5}.*");
    private final Pattern lengthPattern20 = Pattern.compile("[A-Za-z0-9._-]{20}.*");

    @Autowired
    iErrorDAO errorDAO;

    @Override
    public Pattern getPatternForLength(int length) {
        if(length==20){
            return lengthPattern20;
        }else{
            return lengthPattern5;
        }
    }

    @Override
    public List<Error> getAllErrors() {
        return this.errorDAO.getAllErrors();
    }

    @Override
    public List<Error> getAllErrorsForApplication(Application app) {
        return this.errorDAO.getAllErrorsForApplication(app);
    }

    @Override
    public Error getErrorByID(int id) {
        return this.errorDAO.getErrorByID(id);
    }

    @Override
    public boolean addError(Error error) {
        return this.errorDAO.persistError(error);
    }

    @Override
    public boolean updateErrorState(Error error) {
        Error errorFromDB = this.errorDAO.getErrorByID(error.getId());
        errorFromDB.setState(error.getState());
        return this.errorDAO.updateError(errorFromDB);
    }
}
