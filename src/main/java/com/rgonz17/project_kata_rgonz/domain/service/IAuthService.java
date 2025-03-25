package com.rgonz17.project_kata_rgonz.domain.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthService {
    ResponseEntity<Map<String, String>> login(String email, String password);

    ResponseEntity<Map<String, String>> refreshToken(Map<String, String> request);
}
