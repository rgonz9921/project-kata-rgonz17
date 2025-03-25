package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.UserResponse;
import com.rgonz17.project_kata_rgonz.domain.model.*;
import com.rgonz17.project_kata_rgonz.infraestructure.persistence.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ReservationServiceImpl implements IReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IEventService eventService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<UserResponse> addReservation(ReservationRequest reservationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof User)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User authenticatedUser = (User) authentication.getPrincipal();
        logger.info("Authenticated user: {}", authenticatedUser);

        User user = userService.getUserByEmail(authenticatedUser.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Event event = eventService.getEventById(reservationRequest.getEventId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

        validateTicketAvailability(event, reservationRequest.getTicketType(), reservationRequest.getQuantity());
        Reservation savedReservation = createAndSaveReservation(user, event, reservationRequest);
        if (user.getReservations() == null) {
            user.setReservations(new ArrayList<>());
        }
        user.getReservations().add(savedReservation.get_id());
        userService.updateUser(user.get_id(), user);
        UserResponse responseDto = new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getReservations()
        );
        return ResponseEntity.ok(responseDto);
    }

    private TypeTickets validateTicketAvailability(Event event, String ticketType, int requestedQuantity) {
        TypeTickets ticket = event.getTickets().get(ticketType);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ticket type: " + ticketType);
        }
        int availableTickets = ticket.getTotal() - ticket.getSold();
        if (requestedQuantity > availableTickets) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough tickets available");
        }
        ticket.setSold(ticket.getSold() + requestedQuantity);
        event.validateTickets();
        eventService.updateEvent(event.get_id(), event);
        return ticket;
    }

    private Reservation createAndSaveReservation(User user, Event event, ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setDate(new Date());
        reservation.setUserId(user.get_id());
        reservation.setEventId(event.get_id());
        reservation.setTicketType(reservationRequest.getTicketType());
        reservation.setQuantity(reservationRequest.getQuantity());
        return reservationRepository.save(reservation);
    }

}
