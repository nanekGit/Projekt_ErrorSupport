package pl.edu.wszib.support.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(){
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){

        model.addAttribute("programs", new ArrayList<>());



        model.addAttribute("isLogged", this.sessionObject.isLogged());
        if(this.sessionObject.isLogged()) {
            model.addAttribute("role", this.sessionObject.getLoggedUser().getRole().toString());
        }else{
            model.addAttribute("role", null);
        }
        return "main";
    }
}
