package com.rgonz17.project_kata_rgonz.domain.dto;

import java.util.List;

public class UserResponse {
    private String name;
    private String email;
    private List<String> reservations;

    public UserResponse(String name, String email, List<String> reservations) {
        this.name = name;
        this.email = email;
        this.reservations = reservations;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getReservations() { return reservations; }
}
