package com.rgonz17.project_kata_rgonz.domain.exceptions;

public class TicketValidationException extends RuntimeException {
    public TicketValidationException(String message) {
        super(message);
    }
}

