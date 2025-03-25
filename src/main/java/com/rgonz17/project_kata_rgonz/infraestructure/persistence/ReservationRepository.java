package com.rgonz17.project_kata_rgonz.infraestructure.persistence;

import com.rgonz17.project_kata_rgonz.domain.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
