package com.example.HMS.Authentication;

import com.example.HMS.Users.Patient.CustomServiceClass;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticateUser {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtUtils jwtUtils;

    @Autowired
    public CustomServiceClass customServiceClass;

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody UserRequest userRequest){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(), userRequest.getPassword()));
        }
        catch (UsernameNotFoundException e){
            e.getMessage();
        }

        UserDetails user = (UserDetails) customServiceClass.loadUserByUsername(userRequest.getUsername());

       String token = jwtUtils.generateToken(userRequest.getUsername());

       if (token.equals("")){
        return ResponseEntity.status(400).body("No user with that username");
       }
       else {
           var claims = jwtUtils.extractAllClaims(token);
           log.info("Your claims are: {}", claims);
           return ResponseEntity.ok(token);
       }
    }

    @GetMapping("extractClaims/{token}")
    @ResponseBody
    public Claims extractClaimsUsingToken(@PathVariable("token") String token){
        return jwtUtils.extractAllClaims(token);
    }



}
