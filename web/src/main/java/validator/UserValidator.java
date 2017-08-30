package validator;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;
    Pattern pattern = Pattern.compile("[a-zA-Z]*");

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getId().length() > 30) {
            errors.rejectValue("id","Size.user.id");
        }
        if (user.getId().contains(" ")) {
            errors.rejectValue("id","Whitespace.user.id");
        }
        if (userService.find(user.getId()) != null) {
            errors.rejectValue("id","Exist.user.id");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 30) {
            errors.rejectValue("password","Size.user.password");
        }
        if (!user.getConfPassword().equals(user.getPassword())) {
            errors.rejectValue("password", "Different.user.password");
        }

        Matcher matcher1 = pattern.matcher(user.getName());
        if (!matcher1.matches()) {
            errors.rejectValue("name","Incorrect.user.firstName");
        }
        if (user.getId().length() > 20) {
            errors.rejectValue("id","Size.user.firstName");
        }
        Matcher matcher2 = pattern.matcher(user.getLastName());
        if (!matcher2.matches()) {
            errors.rejectValue("lastName","Incorrect.user.lastName");
        }
        if (user.getId().length() > 20) {
            errors.rejectValue("id","Size.user.lastName");
        }
    }
}