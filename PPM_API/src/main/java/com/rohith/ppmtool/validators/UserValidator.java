package com.rohith.ppmtool.validators;

import com.rohith.ppmtool.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    //Support User class
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    //Additional Validations
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if(user.getPassword().length()<6)
            errors.rejectValue("password","Length","password must be at least 6 characters");
        if(!user.getPassword().equals(user.getConfirmPassword()))
            errors.rejectValue("confirmPassword","Match","passwords must match");
    }
}
