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
import pl.edu.wszib.support.model.User;
import pl.edu.wszib.support.services.iApplicationService;
import pl.edu.wszib.support.services.iErrorService;
import pl.edu.wszib.support.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    iApplicationService appService;

    @Autowired
    iErrorService errorService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addApp", method = RequestMethod.GET)
    public String addApplication(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/main";
        }
        model.addAttribute("app", new Application());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", User.Role.ADMIN.toString());
        return "addApp";
    }

    @RequestMapping(value = "/addApp", method = RequestMethod.POST)
    public String addApplicationPOST(@ModelAttribute Application app){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/main";
        }
        if(!this.appService.getPatternForLength(5).matcher(app.getName()).matches()||
                !this.appService.getPatternForLength(3).matcher(app.getType()).matches() ||
                !this.appService.getPatternForLength(20).matcher(app.getDescription()).matches()){
            this.sessionObject.setInfo("Validation failed.");
            return "redirect:http://localhost:8080/addApp";
        }
        if(this.appService.addApplication(app)){
            this.sessionObject.setInfo("Succesfully added new application");
            return "redirect:/main";
        }else{
            this.sessionObject.setInfo("Failed to add new application due to error");
            return "redirect:http://localhost:8080/addApp";
        }
    }

    @RequestMapping(value = "/editError/{id}", method = RequestMethod.GET)
    public String editError(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/main";
        }
        Error error = this.errorService.getErrorByID(id);
        if(error==null){
            return "redirect:/main";
        }
        model.addAttribute("error", error);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", User.Role.ADMIN.toString());
        return "editError";
    }

    @RequestMapping(value = "/editError/{id}", method = RequestMethod.POST)
    public String editErrorPOST(@ModelAttribute Error error, @PathVariable int id){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/main";
        }
        if(error.getId()==0){
            error.setId(id);
        }
        if(this.errorService.updateErrorState(error)){
            this.sessionObject.setInfo("Error's state has been updated");
            return "redirect:/app/"+this.errorService.getErrorByID(id).getApp().getId();
        }else{
            this.sessionObject.setInfo("Failed to update Error's state due to error");
            return "redirect:/editError/"+id;
        }
    }
}
