package pl.edu.wszib.support.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.model.Error;
import pl.edu.wszib.support.services.iApplicationService;
import pl.edu.wszib.support.services.iErrorService;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CommonController {

    @Autowired
    iApplicationService appService;

    @Autowired
    iErrorService errorService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(){
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("apps", this.appService.getAllApplications());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        if(this.sessionObject.isLogged()) {
            model.addAttribute("role", this.sessionObject.getLoggedUser().getRole().toString());
        }else{
            model.addAttribute("role", null);
        }
        return "main";
    }

    @RequestMapping(value = "/app/{id}", method = RequestMethod.GET)
    public String application(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        Application app = this.appService.getApplicationByID(id);
        if(app==null){
            return "redirect:/main";
        }
        model.addAttribute("app", app);
        model.addAttribute("errors", this.errorService.getAllErrorsForApplication(app));
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getLoggedUser().getRole().toString());
        return "app";
    }

    @RequestMapping(value = "/reportError/{id}", method = RequestMethod.GET)
    public String reportError(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        Application app = this.appService.getApplicationByID(id);
        if(app==null){
            return "redirect:/main";
        }
        model.addAttribute("app", app);
        model.addAttribute("error", new Error());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getLoggedUser().getRole().toString());
        return "reportError";
    }

    @RequestMapping(value = "/reportError/{id}", method = RequestMethod.POST)
    public String reportErrorrPOST(@ModelAttribute Error error, @PathVariable int id){
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        Application app = this.appService.getApplicationByID(id);
        if(app==null){
            return "redirect:/main";
        }
        if(!this.errorService.getPatternForLength(5).matcher(error.getTitle()).matches() ||
                !this.errorService.getPatternForLength(20).matcher(error.getDescription()).matches()){
            this.sessionObject.setInfo("Validation failed.");
            return "redirect:/reportError/"+id;
        }
        error.setApp(app);
        error.setUser(this.sessionObject.getLoggedUser());
        error.setState(Error.State.NEW);
        if(this.errorService.addError(error)){
            this.sessionObject.setInfo("Succesyfully added new Error");
            return "redirect:/app/"+id;
        }else{
            this.sessionObject.setInfo("Failed to add new Error due to error");
            return "redirect:/reportError/"+id;
        }
    }
}
