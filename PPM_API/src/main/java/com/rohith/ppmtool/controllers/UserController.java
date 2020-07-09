package com.rohith.ppmtool.controllers;

import com.rohith.ppmtool.config.security.JWTTokenProvider;
import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.payload.JWTLoginSuccessResponse;
import com.rohith.ppmtool.payload.LoginRequest;
import com.rohith.ppmtool.services.UserService;
import com.rohith.ppmtool.services.ValidationErrorService;
import com.rohith.ppmtool.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.rohith.ppmtool.config.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        userValidator.validate(user,result);
        if(result.hasErrors())
          return  validationErrorService.mapErrors(result);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result){
        if(result.hasErrors())
            return validationErrorService.mapErrors(result);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwtToken));
    }
}
