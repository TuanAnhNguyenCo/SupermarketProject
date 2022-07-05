package com.example.demo.Controller;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.service.MyUserDetailService;
import com.example.demo.util.JwtUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUntil jwtUntil;
    @Autowired
    private MyUserDetailService userDetailService;
    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestParam String username ,@RequestParam String password ) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
        }catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        String jwt = jwtUntil.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }
}
