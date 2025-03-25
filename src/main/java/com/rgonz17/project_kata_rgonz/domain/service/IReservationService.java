package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.UserResponse;
import com.rgonz17.project_kata_rgonz.domain.model.*;
import org.springframework.http.ResponseEntity;

public interface IReservationService {
    ResponseEntity<UserResponse> addReservation(ReservationRequest reservationRequest);
}
