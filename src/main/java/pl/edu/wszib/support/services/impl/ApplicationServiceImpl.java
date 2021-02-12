package pl.edu.wszib.support.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.support.dao.iApplicationDAO;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.services.iApplicationService;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ApplicationServiceImpl implements iApplicationService {

    private final Pattern lengthPattern3 = Pattern.compile("[A-Za-z0-9._-]{3}.*");
    private final Pattern lengthPattern5 = Pattern.compile("[A-Za-z0-9._-]{5}.*");
    private final Pattern lengthPattern20 = Pattern.compile("[A-Za-z0-9._-]{20}.*");

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
    public boolean addApplication(Application app) {
        return this.appDAO.persistApplication(app);
    }
}
