package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;
import services.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {
    @Autowired
    private FilesStoreService filesStoreService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello_all", method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView model = new ModelAndView("hello_all");
        model.addObject("firstName",userService.find(name).getName());
        model.addObject("lastName",userService.find(name).getLastName());
        return model;
    }

    @RequestMapping(value = "/hello_all", method = RequestMethod.POST)
    public ModelAndView getFileName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView model = new ModelAndView("hello_all");
        model.addObject("firstName",userService.find(name).getName());
        model.addObject("lastName",userService.find(name).getLastName());
        model.addObject("strings",filesStoreService.findAll(name));
        return model;
    }
}
