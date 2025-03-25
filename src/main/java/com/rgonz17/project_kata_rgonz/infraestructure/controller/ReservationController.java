package com.rgonz17.project_kata_rgonz.infraestructure.controller;

import com.rgonz17.project_kata_rgonz.domain.dto.UserResponse;
import com.rgonz17.project_kata_rgonz.domain.model.*;
import com.rgonz17.project_kata_rgonz.domain.service.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private IReservationService reservationService;

    @PostMapping()
    public ResponseEntity<UserResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        logger.info("START ADD RESERVATION", reservationRequest);
        return reservationService.addReservation(reservationRequest);
    }


    @GetMapping
    public String getReservation(){
        logger.info("START GET RESERVATION");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getEmail();
        }
        return "No se pudo";
    }
}
