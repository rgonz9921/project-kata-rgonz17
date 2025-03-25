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
        return Optional.empty();
    }

    @Override
    public Optional<Event> getEventByName(String id) {
        return Optional.empty();
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(String id, Event event) {
        return null;
    }

    @Override
    public HttpStatus deleteEvent(String id) {
        return HttpStatus.ACCEPTED;
    }

    @Override
    public PagedResponse<Event> searchEventByFilters(String artist, String genre,
                                                     String location, int page, int limit) {
        return null;
    }
}
