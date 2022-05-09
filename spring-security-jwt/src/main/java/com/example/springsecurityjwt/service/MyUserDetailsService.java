package com.example.springsecurityjwt.service;


import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findUserByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return user.map(MyUserDetails::new).get();
    }
}
