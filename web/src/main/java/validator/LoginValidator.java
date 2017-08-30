package validator;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import services.UserService;
import org.springframework.validation.Validator;


@Component
public class LoginValidator implements Validator {

    @Autowired
    private UserService userService;

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
            errors.rejectValue("id", "Size.user.id");
        }
        if (user.getId().contains(" ")) {
            errors.rejectValue("id", "Whitespace.user.id");
        }
        if (userService.find(user.getId()) == null) {
            errors.rejectValue("id", "NotExist.user.id");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 30) {
            errors.rejectValue("password", "Size.user.password");
        }

    }
}
