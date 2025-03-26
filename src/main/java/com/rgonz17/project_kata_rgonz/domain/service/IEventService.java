package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.model.Event;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    PagedResponse<Event> getAllEvents(int page, int limit);

    Optional<Event> getEventById(String id);

    Event createEvent(Event event);

    Event updateEvent(String id, Event event);

    PagedResponse<Event> searchEventByFilters(String artist, String genre, String location, int page, int limit);

    List<Event> searchEventFindByIdIn(List<String> eventIds);
}
