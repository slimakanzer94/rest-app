package com.slimakanzer.restapp.service;

import com.slimakanzer.restapp.entities.User;
import com.slimakanzer.restapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User getUserByUserName(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {
         userRepo.save(user);
    }
}
