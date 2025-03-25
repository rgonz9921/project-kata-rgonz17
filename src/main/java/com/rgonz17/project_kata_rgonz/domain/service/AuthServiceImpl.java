package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.model.Sessions;
import com.rgonz17.project_kata_rgonz.infraestructure.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISessionsService sessionsService;

    @Override
    public ResponseEntity<Map<String, String>> login(String email, String rawPassword) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            logger.error("Error en autenticación: Bad credentials");
            throw new BadCredentialsException("Las credenciales son incorrectas");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, rawPassword));
        String accessToken = jwtUtil.generateToken(userDetails.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
        sessionsService.refresh(new Sessions(userDetails.getUsername(), refreshToken));
        logger.info("token: " + accessToken);
        return ResponseEntity.ok( Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @Override
    public ResponseEntity<Map<String, String>> refreshToken(Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Refresh Token is missing"));
        }
        String email = jwtUtil.extractUsername(refreshToken);
        Optional<Sessions> storedToken = sessionsService.findByEmail(email);
        if (storedToken.isEmpty() || jwtUtil.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid or expired refresh token"));
        }
        String newAccessToken = jwtUtil.generateToken(email);
        String newRefreshToken = jwtUtil.generateRefreshToken(email);
        Sessions sessionsToken =  new Sessions(email, newRefreshToken);
        sessionsService.refresh(sessionsToken);
        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
        ));
    }



}
