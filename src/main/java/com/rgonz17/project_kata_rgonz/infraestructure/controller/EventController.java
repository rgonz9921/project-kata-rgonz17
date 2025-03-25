package com.rgonz17.project_kata_rgonz.infraestructure.controller;

import com.rgonz17.project_kata_rgonz.domain.dto.PagedResponse;
import com.rgonz17.project_kata_rgonz.domain.model.Event;
import com.rgonz17.project_kata_rgonz.domain.service.AuthServiceImpl;
import com.rgonz17.project_kata_rgonz.domain.service.CustomUserDetailsService;
import com.rgonz17.project_kata_rgonz.domain.service.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private IEventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        event.validateTickets();
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event udpateEvent(@PathVariable String id, @RequestBody Event event){
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEvent(@PathVariable String id){
        return eventService.deleteEvent(id);
    }

    @GetMapping
    public PagedResponse<Event> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int limit
    ){
        return eventService.getAllEvents(page, limit);
    }

    @GetMapping("/search")
    public PagedResponse<Event> searchEventByFilters(
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int limit
    ){
        return eventService.searchEventByFilters(artist, genre, location, page, limit);
    }
}
