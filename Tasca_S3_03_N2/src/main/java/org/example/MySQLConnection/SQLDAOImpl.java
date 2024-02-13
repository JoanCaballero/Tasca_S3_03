package org.example.MySQLConnection;


import org.example.Ticket;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDAOImpl implements TicketDAO{
	
	private static File flowerShopDb;
	
	public void mySQLDbCreation() {
		try (Connection connection = mySQLConnection.getConnection()){
			String dropDb = "DROP DATABASE IF EXISTS `flowershop`";
			String createDBSQL = "CREATE SCHEMA IF NOT EXISTS `flowerShop` DEFAULT CHARACTER SET utf8";
			String useSchemaSQL = "USE `flowerShop`";
			String createTicketsTableSQL = "CREATE TABLE IF NOT EXISTS `tickets` ("
					+ " `id` INT PRIMARY KEY,"
					+ " `Total_Value` DECIMAL(10, 2) NOT NULL)";
			connection.createStatement().execute(dropDb);
			connection.createStatement().execute(createDBSQL);
			connection.createStatement().execute(useSchemaSQL);
			connection.createStatement().execute(createTicketsTableSQL);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insertTicket(Ticket ticket) {
		try(Connection connection = mySQLConnection.getConnection()){
			String insertTicketSQL = "INSERT INTO `flowershop`.`tickets` (id, Total_Value) VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTicketSQL);
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
		try(Connection connection = mySQLConnection.getConnection();
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
