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
import pl.edu.wszib.support.dao.iUserDAO;
import pl.edu.wszib.support.dao.iErrorDAO;
import pl.edu.wszib.support.dao.iApplicationDAO;
import pl.edu.wszib.support.model.User;
import pl.edu.wszib.support.model.view.RegistrationModel;
import pl.edu.wszib.support.services.iUserService;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    iUserService userService;

    @MockBean
    iUserDAO userDAO;

    @MockBean
    iErrorDAO errorDAO;

    @MockBean
    iApplicationDAO appDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void testRegister(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("Karol");
        registrationModel.setPass("Karol");
        registrationModel.setPass2("Karol");

        Mockito.when(this.userDAO.getUserByLogin("Karol")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);
        int result = userService.register(registrationModel);
        int expected = 0; //Code responsible for success

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testRegisterLoginTaken(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("Karol1");
        registrationModel.setPass("Karol1");
        registrationModel.setPass2("Karol1");

        Mockito.when(this.userDAO.getUserByLogin("Karol1")).thenReturn(new User());
        int result = userService.register(registrationModel);
        int expected = 1; //Code responsible for login taken

        Assert.assertEquals(expected, result);
    }

    private User generateUser(){
        return new User(5, "mateusz", "mateusz", User.Role.USER);
    }

    @Test
    public void testCorrectAuthenticate(){
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void testIncorrectAuthenticate(){
        User user = new User();
        user.setLogin("mateusz1");
        user.setPass("mateusz1");

        Mockito.when(this.userDAO.getUserByLogin("mateusz1")).thenReturn(null);

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void testIncorrectPassword() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz1");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }
}
