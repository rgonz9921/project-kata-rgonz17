package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.dto.UserDto;
import com.rgonz17.project_kata_rgonz.domain.model.User;

import java.util.Optional;

public interface IUserService {
    PagedResponse<User> getAllUsers(int page, int limit);

    Optional<User> getUserById(String id);

    Optional<User> getUserByEmail(String email);

    User createUser(User user);

    User updateUser(String id, User updateUser);

    PagedResponse<User>  searchUsersByFilters(String name, String email, int page, int limit);

    Optional<UserDto> getUserWithReservations(String email);
}
