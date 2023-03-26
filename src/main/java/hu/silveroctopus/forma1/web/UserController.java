/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.web;

import hu.silveroctopus.forma1.security.MyUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal MyUserPrincipal principal) {

        if (principal == null) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body("authenticated");
        }
    }

    /*@PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // send logout URL to client so they can initiate logout
        String logoutUrl = "thisIsTheLogoutUrl";
        request.getSession(false).invalidate();
        return ResponseEntity.ok().body(thisIsTheLogoutUrl);
    }*/



}
