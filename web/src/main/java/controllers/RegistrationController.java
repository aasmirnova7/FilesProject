package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.UserService;
import validator.UserValidator;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegister(User user) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@Valid  User user, BindingResult result) {

        userValidator.validate(user,result);

        if (result.hasErrors()) {
            return "registration";
        }

        if (!result.hasErrors()) {
            userService.save(user);
        } else {
            return "registration";
        }
        return "redirect:/login";
    }
}
