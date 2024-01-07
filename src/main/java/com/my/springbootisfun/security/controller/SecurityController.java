package com.my.springbootisfun.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    // !! try to change this
    private final String USERNAME = "user";
    private final String PASSWORD = "password";

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Authentication
     * ==============
     */
    @PostMapping("/login")
    public ResponseEntity<String> userLogin() {
        try {
            // approach 1 - same function
//            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(USERNAME, PASSWORD);
//            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

            // approach 2 - same function
            Authentication authenticationResponse = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
            // TODO: to save the authenticated user in the SecurityContextRepository if needed.
            //  For example, if using the HttpSession to persist the SecurityContext between requests,
            //  you can use HttpSessionSecurityContextRepository

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Fail to authenticate");
        }
    }

}
