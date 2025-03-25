package com.rgonz17.project_kata_rgonz.domain.model;

public class ReservationRequest {
    private String eventId;
    private String ticketType;
    private int quantity;

    public ReservationRequest() {
    }

    public ReservationRequest(String eventId, String ticketType, int quantity) {
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.quantity = quantity;
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
}
