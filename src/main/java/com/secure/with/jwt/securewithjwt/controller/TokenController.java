package com.secure.with.jwt.securewithjwt.controller;

import com.secure.with.jwt.securewithjwt.security.JwtUser;
import com.secure.with.jwt.securewithjwt.security.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtValidator jwtValidator;
    @PostMapping()
    public String get(@RequestBody final JwtUser jwtUser) {
       return jwtValidator.getToken(jwtUser);
    }

}
