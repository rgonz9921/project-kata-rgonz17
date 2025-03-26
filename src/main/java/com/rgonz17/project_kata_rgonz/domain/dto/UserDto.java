package com.rgonz17.project_kata_rgonz.domain.dto;

import com.rgonz17.project_kata_rgonz.domain.model.User;

import java.util.List;

public class UserDto {
    private String _id;
    private String name;
    private String email;
    private List<ReservationWithEvent> reservations;

    public UserDto(User user) {
        this._id = user.get_id();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public void setReservations(List<ReservationWithEvent> reservations) {
        this.reservations = reservations;
    }

    // Getters
    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<ReservationWithEvent> getReservations() {
        return reservations;
    }
}
