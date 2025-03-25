package com.rgonz17.project_kata_rgonz.infraestructure.persistence;

import com.rgonz17.project_kata_rgonz.domain.model.Sessions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionRepository  extends MongoRepository<Sessions, String> {
    Optional<Sessions> findByUserEmail(String userEmail);
}
