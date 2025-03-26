package com.rgonz17.project_kata_rgonz.infraestructure.persistence;

import com.rgonz17.project_kata_rgonz.domain.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByUserId(String userId);
}
