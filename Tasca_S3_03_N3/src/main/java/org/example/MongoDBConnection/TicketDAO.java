package org.example.MongoDBConnection;

import org.example.Ticket;

import java.util.List;

public interface TicketDAO {
    void insertTicket(Ticket ticket);
    void mySQLDbCreation();
    List<Ticket> getTickets();
}
