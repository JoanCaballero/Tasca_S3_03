package org.example.MongoDBConnection;


import org.example.Ticket;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MongoDBDAOImpl implements TicketDAO{
	
	private static File flowerShopDb;
	
	public void mySQLDbCreation() {
		try (Connection connection = mongoDBConnection.getConnection()){
			String dropDb = "DROP DATABASE IF EXISTS `flowershop`";
			String createDBMongo = "CREATE SCHEMA IF NOT EXISTS `flowerShop` DEFAULT CHARACTER SET utf8";
			String useSchemaMongo = "USE `flowerShop`";
			String createTicketsTableMongo = "CREATE TABLE IF NOT EXISTS `tickets` ("
					+ " `id` INT PRIMARY KEY,"
					+ " `Total_Value` DECIMAL(10, 2) NOT NULL)";
			connection.createStatement().execute(dropDb);
			connection.createStatement().execute(createDBMongo);
			connection.createStatement().execute(useSchemaMongo);
			connection.createStatement().execute(createTicketsTableMongo);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insertTicket(Ticket ticket) {
		try(Connection connection = mongoDBConnection.getConnection()){
			String insertTicketMongo = "INSERT INTO `flowershop`.`tickets` (id, Total_Value) VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTicketMongo);
			preparedStatement.setInt(1, ticket.getId());
			preparedStatement.setDouble(2, ticket.getPrice());
			preparedStatement.executeUpdate();
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public List<Ticket> getTickets(){
		List<Ticket> ticketList = new ArrayList<>();
		String query = "SELECT id AS ticket_id, Total_Value AS ticket_totalValue FROM flowershop.tickets";
		try(Connection connection = mongoDBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)){
			while(resultSet.next()){
				int id = resultSet.getInt(("ticket_id"));
				double totalValue = resultSet.getDouble("ticket_totalValue");
				Ticket ticket = new Ticket(id, totalValue);
				ticketList.add(ticket);
			}
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return ticketList;
	}

}
