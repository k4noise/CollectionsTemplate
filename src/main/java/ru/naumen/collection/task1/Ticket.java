package ru.naumen.collection.task1;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object ticketObject) {
        if (this == ticketObject) return true;
        if (ticketObject == null || getClass() != ticketObject.getClass()) return false;
        Ticket ticket = (Ticket) ticketObject;
        return id == ticket.id;
    }
}
