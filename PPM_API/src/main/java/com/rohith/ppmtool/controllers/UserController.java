package com.rohith.ppmtool.controllers;

import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.services.UserService;
import com.rohith.ppmtool.services.ValidationErrorService;
import com.rohith.ppmtool.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user,result);
        if(result.hasErrors())
          return  validationErrorService.mapErrors(result);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
