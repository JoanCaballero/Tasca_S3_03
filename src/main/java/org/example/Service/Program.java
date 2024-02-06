package org.example.Service;

import java.util.Scanner;

import org.example.FlowerShop;
import org.example.ProductFactory;
import org.example.Entities.Product;
import org.example.Entities.Tree;

public class Program {
	
	private static FlowerShop fs1 = new FlowerShop("");
	private static ProductFactory productFactory = new ProductFactory();
	
	public static void program() {
		
		boolean chivato = true;
		
		while (chivato) {
			chivato=menu(printingMenu(), chivato);
		}		
	} 
	
	public static int printingMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bienvenido a la aplicacion de gestion de floristerias.\n"
				+ "Seleccione una de las opciones disponibles:\n"
				+ "1. Crear floristeria\n" + "2. Añadir Arbol\n"
				+ "3. Añadir Flor\n" + "4. Añadir Decoracion\n"
				+ "5. Imprimir Stock\n" + "6. Retirar Arbol\n"
				+ "7. Retirar Flor\n" + "8. Retirar Decoracion\n"
				+ "9. Imprimir Stock con cantidades\n" + "10. Imprimir valor total de la floristeria\n"
				+ "11. Crear ticket de compra\n" + "12. Mostrar compras antiguas\n"
				+ "13. Mostrar total de ingresos\n" + "0. Salir de la aplicacion\n");

		return sc.nextInt();
	}
	
	public static boolean menu(int choice, boolean chivato) {
		switch(choice) {
			case 1:
				flowerShopCreation(pedirString("Introduce el nombre de la floristeria"));
				break;
			case 2:
				addTree(fs1, pedirString("Introduce el nombre del arbol"), pedirDouble("Introduce el precio"), pedirDouble("Introduce la altura"));
				break;
			case 3:
				addFlower(fs1, pedirString("Introduce el nombre de la flor"), pedirDouble("Introduce el precio"), pedirString("Introduce el color"));
				break;
			case 4:
				addDecoration(fs1, pedirString("Introduce el nombre de la decoracion"), pedirDouble("Introduce el precio"));
				break;
			case 5:
				listingStock();
				break;
			case 6:
				removeTree();
				break;
			case 7:
				removeFlower();
				break;
			case 8:
				removeDecoration();
				break;
			case 9:
				listingStockQuantitiesV1();
				//listingStockQuantitiesV2();
				break;
			case 10:
				listingTotalEarnings();
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 0:
				chivato=false;
				break;
		}
		return chivato;
	}
	
	public static void flowerShopCreation(String name) {
		fs1.setName(name);
		System.out.println("Se ha creado la floristeria correctamente");
	}
	
	public static void addTree(FlowerShop fs1, String name, double price, double height) {
		Product p = productFactory.createProduct("Tree", name, price, height);
		fs1.addProductStock(p);
		System.out.println("Se ha creado el arbol correctamente");
	}
	
	public static void removeTree() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Este es el listado de arboles");
		fs1.treeProductList();
		
		System.out.println("Introduce el id del arbol que deseas eliminar");
		int id = sc.nextInt();
		
		int indexPosition = searchProductId(id);
		
		if (indexPosition==-1) {
			System.out.println("El arbol no está en el stock.");
		} else {

			Product p = fs1.getProductList().get(indexPosition);
			fs1.removeProductStock(p);
			
			System.out.println("Se ha eliminado el arbol correctamente");
		}
	}
	
	
	public static void addFlower(FlowerShop fs1, String name, double price, String color) {
		Product p = productFactory.createProduct("Flower", name, price, color);
		fs1.addProductStock(p);
		System.out.println("Se ha creado la flor correctamente");
	}
	
	public static void removeFlower() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Este es el listado de flores");
		fs1.flowerProductList();
		
		System.out.println("Introduce el id de la flor que deseas eliminar");
		int id = sc.nextInt();
		
		int indexPosition = searchProductId(id);
		
		if (indexPosition==-1) {
			System.out.println("La flor no está en el stock.");
		} else {

			Product p = fs1.getProductList().get(indexPosition);
			fs1.removeProductStock(p);
			
			System.out.println("Se ha eliminado la flor correctamente");
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
		
		if (indexPosition==-1) {
			System.out.println("La decoracion no está en el stock.");
		} else {

			Product p = fs1.getProductList().get(indexPosition);
			fs1.removeProductStock(p);
			
			System.out.println("Se ha eliminado la decoracion correctamente");
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
		return indexPosition;
	}
	
	public static void listingStock() {
		fs1.productStockList();
	}
	
	public static void listingStockQuantitiesV1() {
		fs1.stockListQuantitiesV1();
	}
	
	public static void listingStockQuantitiesV2() {
		fs1.stockListQuantitiesV2();
	}
	
	
	public static void listingTotalEarnings() {
		System.out.println("El valor total de la floristeria es de " + fs1.getTotalStockValue() + " €");
	}
	
	// Metodo para pedir String 
	static String pedirString(String mensaje) { 
		Scanner sc = new Scanner(System.in);
		System.out.println(mensaje);
		return sc.nextLine();
	}
		
	// Metodo para pedir numero
	static int pedirInt(String mensaje) {
		Scanner sc = new Scanner(System.in);
		System.out.println(mensaje);
		return sc.nextInt();
	}
	
	// Metodo para pedir double
	static double pedirDouble(String mensaje) {
		Scanner sc = new Scanner(System.in);
		System.out.println(mensaje);
		return sc.nextDouble();
	}
}
