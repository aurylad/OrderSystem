package application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Client;
import databse.tables.Orders;
import databse.tables.Product;
import databse.tables.Supplier;

public class Controller {
	
	//Netikri duomenys
	static double productAmount = 22;
	static double productPrice = 50;
	static String productSupplier = "GPS";

	static String clientName = "Pirkėjas";
	static String clientPhoneNumber = "+3707070707";

	static String orderOrderDecsription = "Aprašymas";
	static String orderArrivalDate = "2018";
	
	
	public static void setFormValues() {
		
		Main.getClient().setName(clientName);
		Main.getClient().setPhoneNumber(clientPhoneNumber);
		
		Main.getOrders().setDescriptionOfOrder(orderOrderDecsription);
		Main.getOrders().setDeliveryDate(orderArrivalDate);
		
		Main.getProduct().setAmount(productAmount);
		Main.getProduct().setPrice(productPrice);
		Main.getProduct().setSupplier(productSupplier);
		
		
		Main.getContext().registerShutdownHook();
	}
	
}
