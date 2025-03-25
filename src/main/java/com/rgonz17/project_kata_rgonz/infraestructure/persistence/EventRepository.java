package com.rgonz17.project_kata_rgonz.infraestructure.persistence;

import com.rgonz17.project_kata_rgonz.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String> {

    @Query("{ $and: [ "
            + "{ 'genre': { $regex: ?0, $options: 'i' } }, "
            + "{ 'location': { $regex: ?1, $options: 'i' } } "
            + "{ 'artist': { $regex: ?2, $options: 'i' } } "
            + "] }")
    Page<Event> findByGenreLocationArtist(String genre, String location,String artist, Pageable pageable);
}
