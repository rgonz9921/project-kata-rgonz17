package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.model.User;
import com.rgonz17.project_kata_rgonz.infraestructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private static final int MAX_LIMIT = 50;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PagedResponse<User> getAllUsers(int page, int limit) {
        int finalLimit = Math.min(limit, MAX_LIMIT);
        Pageable pageable = PageRequest.of(page, finalLimit);
        Page<User> userPage = userRepository.findAll(pageable);
        return new PagedResponse<>(
                "users",
                userPage.getContent(),
                page,
                finalLimit,
                userPage.getTotalElements()
        );
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String emailRegex = (email != null) ? ".*" + email + ".*" : ".*";
        return userRepository.findFirstByEmail(emailRegex);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User updateUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setEmail(updateUser.getEmail());
                    user.setPassword(updateUser.getPassword());
                    user.setReservations(updateUser.getReservations());
                    user.setRole(updateUser.getRole());
                    return userRepository.save(user);
                }).orElseGet(() -> {
                    updateUser.set_id(id);
                    return userRepository.save(updateUser);
                });
    }

    @Override
    public PagedResponse<User> searchUsersByFilters(String name, String email, int page, int limit) {
        int finalLimit = Math.min(limit, MAX_LIMIT);
        String nameRegex = (name != null) ? ".*" + name + ".*" : ".*";
        String emailRegex = (email != null) ? ".*" + email + ".*" : ".*";
        Page<User> userPage = userRepository.findByNameAndEmail(nameRegex, emailRegex, PageRequest.of(page, finalLimit));
        return new PagedResponse<>(
                "users",
                userPage.getContent(),
                page,
                finalLimit,
                userPage.getTotalElements()
        );
    }
}
