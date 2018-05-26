package application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.BeanAnnotation;
import beans.BeansInitialization;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static ApplicationContext annotationContext;
	static AbstractApplicationContext beansContext;
	static Supplier supplierObj;
	static Orders ordersBeanObj;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/css/files/MainWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/fxml/css/files/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		BeansInitialization performanceStage = BeansInitialization.getInstance();
		
		// inicijuojamos bean clasės ir bean obijektai
		annotationContext = new AnnotationConfigApplicationContext(BeanAnnotation.class);
		supplierObj = (Supplier) annotationContext.getBean("supplierBean");

		beansContext = new ClassPathXmlApplicationContext("beans/Beans.xml");
		ordersBeanObj = (Orders) beansContext.getBean("ordersBean");
		
		launch(args);
	}

	public static Supplier getSupplierObj() {
		return supplierObj;
	}

	public static Orders getOrdersBeanObj() {
		return ordersBeanObj;
	}

}
