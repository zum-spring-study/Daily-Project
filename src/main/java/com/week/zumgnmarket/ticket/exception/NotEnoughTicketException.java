package com.week.zumgnmarket.ticket.exception;

public class NotEnoughTicketException extends RuntimeException {

    public NotEnoughTicketException() {
        super();
    }

    public NotEnoughTicketException(String message) {
        super(message);
    }

    public NotEnoughTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughTicketException(Throwable cause) {
        super(cause);
    }

}
