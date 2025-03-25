package com.rgonz17.project_kata_rgonz.domain.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
public class Reservation {
    @Id
    private String _id;
    private String eventId;
    private String userId;
    private String ticketType;
    private int quantity;
    private Date date;

    public Reservation() {
    }

    public Reservation(String _id, String eventId, String userId, String ticketType, int quantity, Date date) {
        this._id = _id;
        this.eventId = eventId;
        this.userId = userId;
        this.ticketType = ticketType;
        this.quantity = quantity;
        this.date = date;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
