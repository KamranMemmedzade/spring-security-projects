package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.models.AuthRequest;
import com.example.springsecurityjwt.models.AuthResponse;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class  HomeController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @GetMapping("/")
    public String welcome() {
        return "<h1>Welcome to spring security intro project</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>User page</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Admin page</h1>";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> generateJwt(@RequestBody AuthRequest authRequest)  throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        }catch (BadCredentialsException ex){
            throw new Exception("Incorrect username or password",ex);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
