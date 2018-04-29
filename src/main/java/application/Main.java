package application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Client;
import databse.tables.Orders;
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
	static Orders orders;
	static Product product;

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

		context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		client = (Client) Main.context.getBean("client");
		orders = (Orders) Main.context.getBean("orders");
		product = (Product) Main.context.getBean("product");
		//Supplier supplier = orders.getSupplier(); //Inner bean panaudojimas
		//System.out.println(supplier.getAddress());
		
		//Testavimas ar viskas gerai veikia, sios klases bus iskviestos naudojant interfeisa
		Controller.setFormValues();
		//System.out.println(orders.getDescriptionOfOrder());
		
		TableManager.addAllToDifferentTables();
		
	}

	public static AbstractApplicationContext getContext() {
		return context;
	}

	public static Client getClient() {
		return client;
	}

	public static Orders getOrders() {
		return orders;
	}

	public static Product getProduct() {
		return product;
	}

}
