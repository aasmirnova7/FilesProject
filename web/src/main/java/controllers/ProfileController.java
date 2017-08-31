package controllers;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    Pattern pattern = Pattern.compile("[a-zA-Z]*");

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfilePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView model = new ModelAndView("profile");
        model.addObject("lastName",userService.find(name).getLastName());
        model.addObject("name",userService.find(name).getName());
        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView changeLastName(@RequestParam String newLastName, @RequestParam String newName,
                                       @RequestParam String repeatPassword, @RequestParam String newPassword) {
        ModelAndView model = new ModelAndView("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();

        Matcher matcher1 = pattern.matcher(newLastName);
        if(!newLastName.equals("") && matcher1.matches()){
            userService.changeLastName(id,newLastName);
        }
        if(!matcher1.matches()){
            model.addObject("errorLastName","Use just letters!");
        }

        matcher1 = pattern.matcher(newName);
        if(!newName.equals("")&& matcher1.matches()){
            userService.changeFirstName(id,newName);
        }
        if(!matcher1.matches()){
            model.addObject("errorFirstName","Use just letters!");
        }

        if(newPassword.length() > 5 && repeatPassword.equals(newPassword)){
            userService.changePassword(id,newPassword);
            model.addObject("password","Password Change!");
        }
        if(!repeatPassword.equals(newPassword)){
            model.addObject("errorRepeatPassword","New password and repeat password don't equals");
        }
        if(newPassword.length() < 6 && newPassword.length() > 0){
            model.addObject("errorPassword","Password must be between 6 and 30 characters!");
        }
        model.addObject("name",userService.find(id).getName());
        model.addObject("lastName",userService.find(id).getLastName());
        return model;
    }

//    @RequestMapping(value = "/profile", method = RequestMethod.POST)
//    public ModelAndView changeFirstName(@RequestParam String newName) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String id = auth.getName();
//        userService.changeFirstName(id,newName);
//        ModelAndView model = new ModelAndView("profile");
//        model.addObject("name",userService.find(id).getName());
//        model.addObject("lastName",userService.find(id).getLastName());
//        return model;
//    }


}
