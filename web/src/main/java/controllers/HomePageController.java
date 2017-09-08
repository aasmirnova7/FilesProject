package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;
import services.UserService;

import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private FilesStoreService filesStoreService;
    @Autowired
    private UserService userService;

    private ModelAndView updateModel(ModelAndView model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        model.addObject("firstName",userService.find(id).getName());
        model.addObject("lastName",userService.find(id).getLastName());
        return model;
    }
    @RequestMapping(value = "/hello_all", method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        return updateModel(new ModelAndView("hello_all"));
    }

    @RequestMapping(value = "/hello_all", method = RequestMethod.POST)
    public ModelAndView showFiles(@RequestParam String action) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("hello_all");
        if (action.equals("Delete account")) {
            userService.delete(id);
            return new ModelAndView("login");
        } else if(action.equals("Show all files")) {
            List<String> myfiles = filesStoreService.findAll(id);
            List<String> filesICanSee = filesStoreService.findAllInSpecialFiles(id);
            if(!myfiles.isEmpty()) {
                model.addObject("lable1", "My files: ");
                model.addObject("strings", myfiles);
            }
            if(!filesICanSee.isEmpty()) {
                model.addObject("lable2", "Files that I can see: ");
                model.addObject("files", filesICanSee);
            }
            if(myfiles.isEmpty() && filesICanSee.isEmpty()){
                model.addObject("error", "You have not files!");
            }
        }
        return updateModel(model);
    }
}
