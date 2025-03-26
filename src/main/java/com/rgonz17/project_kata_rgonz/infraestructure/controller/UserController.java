package com.rgonz17.project_kata_rgonz.infraestructure.controller;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.dto.UserDto;
import com.rgonz17.project_kata_rgonz.domain.model.User;
import com.rgonz17.project_kata_rgonz.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public PagedResponse<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int limit) {
        return userService.getAllUsers(page, limit);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/searchByEmail")
    public Optional<User> getUserByEmail(@RequestParam(required = true) String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/reservation")
    public  Optional<UserDto> getReservationByUserEmail(@RequestParam(required = true) String email){
        return userService.getUserWithReservations(email);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @GetMapping("/search")
    public PagedResponse<User> searchUsersByFilters(@RequestParam(required = false) String name_like,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "50") int limit) {

        return userService.searchUsersByFilters(name_like, email, page, limit);
    }


}
