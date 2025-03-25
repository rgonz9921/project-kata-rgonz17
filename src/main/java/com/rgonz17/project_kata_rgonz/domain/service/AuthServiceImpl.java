package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.infraestructure.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> login(String email, String rawPassword) {
        logger.info("email: {}", email);
        logger.info("password: {}", rawPassword);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        logger.info("UserDetails: {}", userDetails);
        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            logger.error("Error en autenticación: Bad credentials");
            throw new BadCredentialsException("Las credenciales son incorrectas");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, rawPassword));
        String token = jwtUtil.generateToken(userDetails.getUsername());
        logger.info("token: " + token);
        return Map.of("token", token);
    }
}
