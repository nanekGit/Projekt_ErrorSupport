package pl.edu.wszib.support.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.support.dao.iApplicationDAO;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.services.iApplicationService;
import pl.edu.wszib.support.session.SessionObject;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ApplicationServiceImpl implements iApplicationService {

    private final Pattern lengthPattern3 = Pattern.compile("[ '`\\p{L}\\p{M}0-9_.-]{3}.*");
    private final Pattern lengthPattern5 = Pattern.compile("[ '`\\p{L}\\p{M}0-9_.-]{5}.*");
    private final Pattern lengthPattern20 = Pattern.compile("[ '`\\p{L}\\p{M}0-9_.-]{20}.*");

    @Autowired
    iApplicationDAO appDAO;

    @Override
    public Pattern getPatternForLength(int length) {
        if(length==3){
            return lengthPattern3;
        }else if(length==20){
            return lengthPattern20;
        }else{
            return lengthPattern5;
        }
    }

    @Override
    public List<Application> getAllApplications() {
        return this.appDAO.getAllApplications();
    }

    @Override
    public Application getApplicationByID(int id) {
        return this.appDAO.getApplicationByID(id);
    }

    @Override
    public Application getApplicationByName(String name) {
        return this.appDAO.getApplicationByName(name);
    }

    @Override
    public int addApplication(Application app) {
        if(this.appDAO.getApplicationByName(app.getName())!=null){
            return 1;
        }
        if(this.appDAO.persistApplication(app)){
            return 0;
        }
        return 2;
    }
}
