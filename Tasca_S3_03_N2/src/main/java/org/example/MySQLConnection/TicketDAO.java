package org.example.MySQLConnection;

import org.example.Ticket;

import java.sql.Connection;
import java.util.List;

public interface TicketDAO {
    void insertTicket(Ticket ticket);
    void mySQLDbCreation();
    List<Ticket> getTickets();
}
