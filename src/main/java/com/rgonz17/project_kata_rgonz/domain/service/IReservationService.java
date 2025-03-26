package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.UserResponse;
import com.rgonz17.project_kata_rgonz.domain.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReservationService {
    ResponseEntity<UserResponse> addReservation(ReservationRequest reservationRequest);

    List<Reservation> reservationByUserId(String id);

}
