/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.controller;

import hu.silveroctopus.forma1.security.MyUserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.util.Objects.nonNull;

@RestController
public class UserController {

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal MyUserPrincipal principal) {
        return nonNull(principal) ?
                ResponseEntity.ok().body("authenticated") :
                new ResponseEntity<>("", HttpStatus.OK);
    }
}
