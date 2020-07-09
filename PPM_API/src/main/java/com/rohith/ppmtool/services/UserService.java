package com.rohith.ppmtool.services;

import com.rohith.ppmtool.exceptions.UsernameAlreadyExistsException;
import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User saveUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            return userRepository.save(user);
        }
        catch(Exception exe){
            System.out.println(exe.getMessage());
           throw new UsernameAlreadyExistsException("The emailId "+user.getUsername()+" already exists,Please use other email id");
        }

    }


}
