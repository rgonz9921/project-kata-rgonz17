package com.rgonz17.project_kata_rgonz.infraestructure.controller;

import com.rgonz17.project_kata_rgonz.domain.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        return authService.login(credentials.get("email"), credentials.get("password"));
    }
}
