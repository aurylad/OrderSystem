package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Client;
import databse.tables.Product;
import databse.tables.Supplier;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static AbstractApplicationContext context;
	static Client client;
	//static OrdersSingle orders;
	static Product product;
	//static ArrivalDate arrivalDate;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Form.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

//		context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
//		client = (Client2) Main.context.getBean("client");
//		orders = (OrdersSingle) Main.context.getBean("ordersSingle");
//		product = (Product2) Main.context.getBean("product"); 
//		arrivalDate = (ArrivalDate) Main.context.getBean("arrivalDate"); // autowire
		//Supplier supplier = orders.getSupplier(); // Inner bean 
		//System.out.println(supplier.getAddress());
		
		//Controller.setFormValues();
		//System.out.println(orders.getDescriptionOfOrder());
		//TableManager.addAllToDifferentTables();
		//TableManager.querySelect();
		//System.out.println(arrivalDate.getOrders().getDeliveryDate()); // autowire
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		Date date = new Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
	}

	public static AbstractApplicationContext getContext() {
		return context;
	}

	public static Client getClient() {
		return client;
	}

//	public static OrdersSingle getOrders() {
//		return orders;
//	}

	public static Product getProduct() {
		return product;
	}

}
