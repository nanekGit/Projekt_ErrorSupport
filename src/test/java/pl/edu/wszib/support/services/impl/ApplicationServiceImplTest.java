package pl.edu.wszib.support.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.support.configuration.AppConfigurationTest;
import pl.edu.wszib.support.dao.iApplicationDAO;
import pl.edu.wszib.support.dao.iErrorDAO;
import pl.edu.wszib.support.dao.iUserDAO;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.services.iApplicationService;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebAppConfiguration
public class ApplicationServiceImplTest {

    @Autowired
    iApplicationService appService;

    @MockBean
    iApplicationDAO appDAO;

    @MockBean
    iErrorDAO errorDAO;

    @MockBean
    iUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void testAddApp(){
        Application app = new Application();
        app.setName("APP Name");
        app.setType("TYPE");
        app.setDescription("Experimental Description minimum 20 chars");

        Mockito.when(this.appDAO.getApplicationByName("APP Name")).thenReturn(null);
        Mockito.when(this.appDAO.persistApplication(ArgumentMatchers.any())).thenReturn(true);
        int result = appService.addApplication(app);
        int expected = 0; //Code responsible for success

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddAppNameTaken(){
        Application app = new Application();
        app.setName("APP Name 2");
        app.setType("TYPE");
        app.setDescription("Experimental Description minimum 20 chars");

        Mockito.when(this.appDAO.getApplicationByName("APP Name 2")).thenReturn(new Application());
        int result = appService.addApplication(app);
        int expected = 1; //Code responsible for name taken

        Assert.assertEquals(expected, result);
    }
}
