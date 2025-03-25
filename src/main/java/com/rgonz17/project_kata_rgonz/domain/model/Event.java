package com.rgonz17.project_kata_rgonz.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rgonz17.project_kata_rgonz.domain.exceptions.TicketValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "events")
public class Event {

    @Id
    private String _id;

    private String name;
    private List<String> genres;

    private Date released;

    private Date dateOfEvent;

    private List<String> artist;

    private String location;
    private Map<String, TypeTickets> tickets;

    public Event(){
    }

    public Event(String _id, String name, List<String> genres, Date released, Date dateOfEvent, List<String> artist, String location, Map<String, TypeTickets> tickets) {
        this._id = _id;
        this.name = name;
        this.genres = genres;
        this.released = released;
        this.dateOfEvent = dateOfEvent;
        this.artist = artist;
        this.location = location;
        this.tickets = tickets;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, TypeTickets> getTickets() {
        return tickets;
    }

    public void setTickets(Map<String, TypeTickets> tickets) {
        this.tickets = tickets;
    }

    public void validateTickets() {
        if (tickets != null) {
            tickets.forEach((tipo, ticket) -> {
                if (ticket.getSold() > ticket.getTotal()) {
                    throw new TicketValidationException(
                            "El número de boletos vendidos para " + tipo + " no puede ser mayor que el total disponible.");
                }
            });
        }
    }
}
