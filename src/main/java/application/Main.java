package application;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import autowire.Cleaner;
import autowire.Manager;
import autowire.Seller;
import autowire.Storekeeper;
import beans.BeanAnnotation;
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
	static Scene scene;

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/css/files/MainWindow.fxml"));
			scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("/fxml/css/files/redButton.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
	}

	public static void main(String[] args) {

		// inicijuojamos bean clasės ir bean obijektai
		annotationContext = new AnnotationConfigApplicationContext(BeanAnnotation.class);
		supplierObj = (Supplier) annotationContext.getBean("supplierBean");

		beansContext = new ClassPathXmlApplicationContext("beans/Beans.xml");
		ordersBeanObj = (Orders) beansContext.getBean("ordersBean");

		// autowire byName testing
		Manager managerBean = (Manager) beansContext.getBean("manager");
		System.out.println(managerBean.toString());

		// autowire byType testing
		Seller sellerBean = (Seller) beansContext.getBean("seller");
		sellerBean.getPersonalData().setPosting("seller");
		sellerBean.getPersonalData().setWorkExperience("seller");
		System.out.println(sellerBean.toString());

		// autowire constructor testing
		Storekeeper storekeeperBean = (Storekeeper) beansContext.getBean("storekeeper");
		storekeeperBean.getPersonalData().setPosting("storekeeper");
		storekeeperBean.getPersonalData().setWorkExperience("storekeeper");
		System.out.println(storekeeperBean.toString());

		// autowire @Autowired annotation testing
		Cleaner cleanerBean = (Cleaner) beansContext.getBean("cleaner");
		cleanerBean.getPersonalData().setPosting("cleaner");
		cleanerBean.getPersonalData().setWorkExperience("cleaner");
		System.out.println(cleanerBean.toString());

		launch(args);
	}

	public static Supplier getSupplierObj() {
		return supplierObj;
	}

	public static Orders getOrdersBeanObj() {
		return ordersBeanObj;
	}

	public static Scene getScene() {
		return scene;
	}

}
