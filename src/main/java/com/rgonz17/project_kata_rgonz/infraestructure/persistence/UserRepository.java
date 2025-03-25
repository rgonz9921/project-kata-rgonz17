package com.rgonz17.project_kata_rgonz.infraestructure.persistence;

import com.rgonz17.project_kata_rgonz.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    Page<User> findAll(Pageable pageable);

    @Query("{ $and: [ "
            + "{ 'name': { $regex: ?0, $options: 'i' } }, "
            + "{ 'email': { $regex: ?1, $options: 'i' } } "
            + "] }")
    Page<User> findByNameAndEmail(String nameRegex, String emailRegex, Pageable pageable);

    @Query("{ 'email': { $regex: ?0, $options: 'i' } }")
    Optional<User> findFirstByEmail(String emailRegex);

    Optional<User> findByEmail(String email);
}
