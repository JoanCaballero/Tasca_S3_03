package org.example.MongoDBConnection;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.Ticket;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOImpl implements TicketDAO{
	
	private MongoCollection<Document> ticketCollection;
	private MongoDatabase db;
	public MongoDBDAOImpl(){
		this.db = mongoDBConnection.getConnection();
	}
	
	public void mongoDBCreation() {
		db.drop();
		db.createCollection("ticketCollection");
		this.ticketCollection = db.getCollection("ticketCollection");
	}
	
	public void insertTicket(Ticket ticket) {ticketCollection.insertOne(ticketToDocument(ticket));}

	public List<Ticket> getTickets(){
		List<Ticket> ticketList = new ArrayList<>();
		try (MongoCursor<Document> cursor = ticketCollection.find().iterator()) {
			while (cursor.hasNext()) {
				Document ticketDoc = cursor.next();
				ticketList.add(documentToTicket(ticketDoc));
			}
		}
		return ticketList;
	}

	private Ticket documentToTicket(Document document) {
		int id = document.getInteger("id");
		double totalValue = document.getDouble("Total Value");
		return new Ticket(id, totalValue);
	}

	private Document ticketToDocument(Ticket ticket){
		return new Document("id", ticket.getId()).append("Total Value", ticket.getPrice());
	}

}
