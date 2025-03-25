package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.model.User;
import com.rgonz17.project_kata_rgonz.infraestructure.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email) // Busca por email
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        logger.info("Usuario: " + user);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
