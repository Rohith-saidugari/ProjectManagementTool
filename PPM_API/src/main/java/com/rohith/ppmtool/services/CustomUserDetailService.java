package com.rohith.ppmtool.services;

import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        return user;
    }

    @Transactional
    public UserDetails loadUserById(long id){
        User user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        return user;
    }
}
