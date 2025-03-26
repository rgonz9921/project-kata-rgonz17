package com.rgonz17.project_kata_rgonz.domain.dto;

import com.rgonz17.project_kata_rgonz.domain.model.Event;
import com.rgonz17.project_kata_rgonz.domain.model.Reservation;

public class ReservationWithEvent {
    private String _id;
    private String ticketType;
    private int quantity;
    private Event event;

    public ReservationWithEvent(Reservation reservation, Event event) {
        this._id = reservation.get_id();
        this.ticketType = reservation.getTicketType();
        this.quantity = reservation.getQuantity();
        this.event = event;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}