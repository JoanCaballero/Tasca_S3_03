package org.example.Service;

import org.example.Entities.Decoration;
import org.example.Entities.Flower;
import org.example.Entities.Product;
import org.example.Entities.Tree;
import org.example.FlowerShop;
import org.example.ProductFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
	
	private static FlowerShop fs1 = new FlowerShop("");
	private static ProductFactory productFactory = new ProductFactory();
	
	public static void program() {
		
		System.out.println("Bienvenido a la aplicacion de gestion de floristerias");
		
		boolean chivato = true;
		
		while (chivato) {
			chivato=menu(printingMenu(), chivato);
		}		
	} 
	
	public static int printingMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("""
				
				Seleccione una de las opciones disponibles:
				1. Crear floristeria
				2. Añadir Arbol
				3. Añadir Flor
				4. Añadir Decoracion
				5. Imprimir Stock
				6. Retirar Arbol
				7. Retirar Flor
				8. Retirar Decoracion
				9. Imprimir Stock con cantidades
				10. Imprimir valor total de la floristeria
				11. Crear ticket de compra
				12. Mostrar compras antiguas
				13. Mostrar total de ingresos
				0. Salir de la aplicacion
				""");

		return sc.nextInt();
	}
	
	public static boolean menu(int choice, boolean chivato) {
		switch (choice) {
			case 1 -> flowerShopCreation();
			case 2 -> addTree(fs1, pedirString("Introduce el nombre del arbol"),
					pedirDouble("Introduce el precio"),
					pedirDouble("Introduce la altura"));
			case 3 -> addFlower(fs1, pedirString("Introduce el nombre de la flor"),
					pedirDouble("Introduce el precio"),
					pedirString("Introduce el color"));
			case 4 -> addDecoration(fs1, pedirString("Introduce el nombre de la decoracion"),
					pedirDouble("Introduce el precio"));
			case 5 -> listingStock();
			case 6 -> removeTree();
			case 7 -> removeFlower();
			case 8 -> removeDecoration();
			case 9 -> listingStockQuantitiesV2();
			case 10 -> listingTotalValue();
			case 11 -> createTicket();
			case 12 -> listingTickets();
			case 13 -> listingTotalEarnings();
			case 0 -> chivato = false;
		}
		return chivato;
	}
	
	public static void flowerShopCreation() {
		if (fs1.getName().isBlank()) {
			fs1.setName(pedirString("Introduce el nombre de la floristeria"));
			System.out.println("Se ha creado la floristeria correctamente");
		} else {
			System.out.println("Ya existe una floristeria. Introduce productos.");
		}
	}
	
	public static void addTree(FlowerShop fs1, String name, double price, double height) {
		Product p = productFactory.createProduct("Tree", name, price, height);
		fs1.addProductStock(p);
		System.out.println("Se ha creado el " + p.getName() + " correctamente");
	}
	
	public static void removeTree() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Este es el listado de arboles");
		fs1.treeProductList();
		
		System.out.println("Introduce el id del arbol que deseas eliminar");
		int id = sc.nextInt();
		
		int indexPosition = searchProductId(id);
		
		if (indexPosition!=-1) {
			Product p = fs1.getProductList().get(indexPosition);
		
			if (!(p instanceof Tree)) {
				System.out.println("No estas seleccionando un arbol");
			} else {
				fs1.removeProductStock(p);
				System.out.println("Se ha eliminado el arbol correctamente");
			}
		}
	}
	
	
	public static void addFlower(FlowerShop fs1, String name, double price, String color) {
		Product p = productFactory.createProduct("Flower", name, price, color);
		fs1.addProductStock(p);
		System.out.println("Se ha creado la " + p.getName() + " correctamente");
	}
	
	public static void removeFlower() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Este es el listado de flores");
		fs1.flowerProductList();
		
		System.out.println("Introduce el id de la flor que deseas eliminar");
		int id = sc.nextInt();
		
		int indexPosition = searchProductId(id);
				
		if (indexPosition!=-1) {
			Product p = fs1.getProductList().get(indexPosition);
		
			if (!(p instanceof Flower)) {
				System.out.println("No estas seleccionando una flor");
			} else {
				fs1.removeProductStock(p);
				System.out.println("Se ha eliminado la flor correctamente");
			}
		}
	}
	
	public static void addDecoration(FlowerShop fs1, String name, double price) {
		Scanner sc = new Scanner(System.in);
		boolean wood = true;
		
		String respuesta = pedirString("¿Es mandera? Si / No");
		if (respuesta.equalsIgnoreCase("No")) {
			wood = false;
		}
		Product p = productFactory.createProduct("Decoration", name, price, wood);
		fs1.addProductStock(p);
		System.out.println("Se ha creado la decoracion correctamente");
	}
	
	public static void removeDecoration() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Este es el listado de objetos de decoracion");
		fs1.decorationProductList();
		
		System.out.println("Introduce el id de la decoracion que deseas eliminar");
		int id = sc.nextInt();
		
		int indexPosition = searchProductId(id);
		
		if (indexPosition!=-1) {
			Product p = fs1.getProductList().get(indexPosition);
		
			if (!(p instanceof Decoration)) {
				System.out.println("No estas seleccionando un objeto decoracion");
			} else {
				fs1.removeProductStock(p);
				System.out.println("Se ha eliminado el objeto decoracion correctamente");
			}
		}
	}
	
	public static int searchProductId(int id) {
		int indexPosition = -1;
		int i = 0;
		
		while (i<fs1.getProductList().size() && indexPosition==-1) {
			if (fs1.getProductList().get(i).getId()==id) {
				indexPosition=i;
			}
			i++;
		}
		if(indexPosition == -1){
			System.out.println("El producto seleccionado no está en el stock.");
		}
		return indexPosition;
	}
	
	public static void listingStock() {
		fs1.productStockList();
	}
	
	public static void listingStockQuantitiesV2() {
		fs1.stockListQuantitiesV2();
	}
	
	
	public static void listingTotalValue() {
		System.out.println("El valor total de la floristeria es de " + fs1.getTotalStockValue() + "€");
	}

	public static void listingTotalEarnings() {
		System.out.println("El total de los productos vendidos en la floristeria asciende a " + fs1.getTotalEarnings() + "€");
	}

	public static void createTicket(){ fs1.createTicket();}
	
	public static void listingTickets() {fs1.showTickets(); }
	
	// Metodo para pedir String 
	static String pedirString(String mensaje) {
		Scanner sc = new Scanner(System.in);
		System.out.println(mensaje);
		return sc.nextLine();
	}
		
	// Metodo para pedir numero
	public static int pedirInt(String mensaje) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		boolean correct = false;
		do{
			System.out.println(mensaje);
			try{
				num = sc.nextInt();
				correct = true;
			}catch (InputMismatchException e){
				e.getStackTrace();
			}
			sc.nextLine();
		}while(!correct);
		return num;
	}
	
	// Metodo para pedir double
	static double pedirDouble(String mensaje) {
		Scanner sc = new Scanner(System.in);
		double num = 0.0;
		boolean correct = false;
		do{
			System.out.println(mensaje);
			try{
				num = sc.nextDouble();
				correct = true;
			}catch (InputMismatchException e){
				e.getStackTrace();
			}
			sc.nextLine();
		}while(!correct);
		return num;
	}
}
