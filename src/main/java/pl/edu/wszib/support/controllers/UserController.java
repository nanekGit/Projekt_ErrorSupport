package pl.edu.wszib.support.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.support.model.User;
import pl.edu.wszib.support.model.view.RegistrationModel;
import pl.edu.wszib.support.services.iUserService;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    iUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("userModel", new User());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", null);
        model.addAttribute("info", this.sessionObject.getInfo());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute User user){
        Pattern regexp = this.userService.getLengthPattern();
        Matcher loginMatcher = regexp.matcher(user.getLogin());
        Matcher passMatcher = regexp.matcher(user.getPass());

        if(!loginMatcher.matches() || !passMatcher.matches())
        {
            this.sessionObject.setInfo("Błąd Validacji.");
            return "redirect:http://localhost:8080/login";
        }
        this.userService.authenticate(user);
        if(!this.sessionObject.isLogged()){
            this.sessionObject.setInfo("Błędny login lub hasło.");
            return "redirect:http://localhost:8080/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.userService.logout();
        return "redirect:http://localhost:8080/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("RegistrationModel", new RegistrationModel());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", null);
        model.addAttribute("info", this.sessionObject.getInfo());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute RegistrationModel registrationModel){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        Pattern regexp = this.userService.getLengthPattern();
        Matcher loginMatcher = regexp.matcher(registrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(registrationModel.getPass());
        Matcher pass2Matcher = regexp.matcher(registrationModel.getPass2());

        if(!registrationModel.getPass().equals(registrationModel.getPass2()) ||
                !loginMatcher.matches() ||
                !passMatcher.matches() ||
                !pass2Matcher.matches())
        {
            this.sessionObject.setInfo("Błąd Validacji.");
            return "redirect:http://localhost:8080/register";
        }
        if(!this.userService.register(registrationModel)){
            this.sessionObject.setInfo("Login Zajęty.");
            return "redirect:http://localhost:8080/register";
        }
        return "redirect:http://localhost:8080/login";
    }
}
