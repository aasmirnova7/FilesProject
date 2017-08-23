package controllers;

import dao.daointerfaces.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;


@Controller
public class RegistrationController {
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegister() {
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, ModelMap model, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (userDao.find(user.getId())!=null){
            return "redirect:/repeat_registration_error";
        }
        model.addAttribute("id",user.getId());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("name", user.getName());
        model.addAttribute("lastName", user.getLastName());
        if (!result.hasErrors()&&!user.getId().equals("")&&!user.getName().equals("")&&!user.getLastName().equals("")&&!user.getPassword().equals("")) {
            userDao.save(user);
        }
        else {
            return "registration";
        }

        return "redirect:/login";
    }
}
