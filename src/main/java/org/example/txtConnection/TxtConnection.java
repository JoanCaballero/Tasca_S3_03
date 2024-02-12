package org.example.txtConnection;


import org.example.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TxtConnection {
	
	private static File flowerShopDb;
	
	public static void txtDbCreation() {
		flowerShopDb = new File("FlowerShopDB.txt");
		PrintWriter salida = null;
		
		try {
			salida = new PrintWriter(flowerShopDb);
			System.out.println("La base de datos para almacenar los tickets se ha creado correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			salida.close();
		}
		
	}
	
	public static void txtDbWriting(Ticket ticket) {
				
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(flowerShopDb, true); // false para que se cree un nuevo archivo
				fw.write(ticket.TicketStringBuilder() + "\n");
			
			System.out.println("El ticket se ha creado correctamete");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
