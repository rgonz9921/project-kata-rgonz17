package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.model.Event;
import com.rgonz17.project_kata_rgonz.infraestructure.persistence.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventServiceImpl implements IEventService {

    private static final Logger logger = Logger.getLogger(EventServiceImpl.class.getName());
    private static final int MAX_LIMIT = 10;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public PagedResponse<Event> getAllEvents(int page, int limit) {
        int finalLimit = Math.min(limit, MAX_LIMIT);
        Pageable pageable = PageRequest.of(page, finalLimit);
        Page<Event> eventPage = eventRepository.findAll(pageable);
        return new PagedResponse<>(
                "event",
                eventPage.getContent(),
                page,
                finalLimit,
                eventPage.getTotalElements()
        );
    }

    @Override
    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(String id, Event updateEvent) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setName(updateEvent.getName());
                    event.setTickets(updateEvent.getTickets());
                    event.setDateOfEvent(updateEvent.getDateOfEvent());
                    event.setArtist(updateEvent.getArtist());
                    event.setGenres(updateEvent.getGenres());
                    event.setReleased(updateEvent.getReleased());
                    event.setLocation(updateEvent.getLocation());
                    return eventRepository.save(event);
                }).orElseGet(() -> {
                    updateEvent.set_id(id);
                    return eventRepository.save(updateEvent);
                });
    }

    @Override
    public PagedResponse<Event> searchEventByFilters(String artist, String genre,
                                                     String location, int page, int limit) {
        int finalLimit = Math.min(limit, MAX_LIMIT);
        String genreRegex = (genre != null) ? ".*" + genre + ".*" : ".*";
        String locationRegex = (location != null) ? ".*" + location + ".*" : ".*";
        String artistRegex = (artist != null) ? ".*" + artist + ".*" : ".*";
        Page<Event> eventPage = eventRepository.findByGenreLocationArtist(
                genreRegex, locationRegex, artistRegex, PageRequest.of(page, finalLimit));
        return new PagedResponse<>(
                "event",
                eventPage.getContent(),
                page,
                finalLimit,
                eventPage.getTotalElements()
        );
    }
}
