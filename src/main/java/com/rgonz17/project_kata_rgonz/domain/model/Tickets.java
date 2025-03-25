package com.rgonz17.project_kata_rgonz.domain.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Tickets {

    private Map<String, TypeTickets> ticketTypes = new HashMap<>();

    public Tickets() {
    }

    @JsonAnyGetter
    public Map<String, TypeTickets> getTicketTypes() {
        return ticketTypes;
    }

    @JsonAnySetter
    public void setTicketType(String key, TypeTickets value) {
        this.ticketTypes.put(key, value);
    }
}
