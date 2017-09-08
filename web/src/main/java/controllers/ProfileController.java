package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    private Pattern pattern = Pattern.compile("[a-zA-Z]*");

    private ModelAndView updateModel(ModelAndView model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        model.addObject("lastName",userService.find(id).getLastName());
        model.addObject("name",userService.find(id).getName());
        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfilePage() {
        return updateModel(new ModelAndView("profile"));
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView changeProfile(@RequestParam String newLastName, @RequestParam String newName,
                                      @RequestParam String repeatPassword, @RequestParam String newPassword) {
        ModelAndView model = new ModelAndView("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();

        Matcher matcher1 = pattern.matcher(newLastName);
        if(!newLastName.equals("")){
            if(matcher1.matches()&&newLastName.length()<20) userService.changeLastName(id, newLastName);
            else  model.addObject("errorLastName"," Use just letters and size need to be less than 20!");
        }

        matcher1 = pattern.matcher(newName);
        if(!newName.equals("")){
            if(matcher1.matches()&&newName.length()<20) userService.changeFirstName(id,newName);
            else model.addObject("errorFirstName"," Use just letters and size need to be less than 20!");
        }

        if(newPassword.length() > 5 && repeatPassword.equals(newPassword)){
            userService.changePassword(id,newPassword);
            model.addObject("password"," Password Change!");
        }
        if(!repeatPassword.equals(newPassword)){
            model.addObject("errorRepeatPassword"," New password and repeat password don't equals");
        }
        if(newPassword.length() < 6 && newPassword.length() > 0){
            model.addObject("errorPassword"," Password must be between 6 and 30 characters!");
        }
        return updateModel(model);
    }
}
