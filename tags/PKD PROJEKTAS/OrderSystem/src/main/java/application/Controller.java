package application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Client;
import databse.tables.OrdersSingle;
import databse.tables.Product;
import databse.tables.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

	// Table Employees
	@FXML
	TableView tableOrders;
	@FXML
	TableColumn<?, ?> columnId;
	@FXML
	TableColumn<?, ?> columnDescription;
	@FXML
	TableColumn<?, ?> columnPhone;
	@FXML
	TableColumn<?, ?> columnClientName;
	@FXML
	TableColumn<?, ?> columnAmount;
	@FXML
	TableColumn<?, ?> columnPrice;
	@FXML
	TableColumn<?, ?> columnSupplier;
	@FXML
	TableColumn<?, ?> columnDevDate;
	@FXML
	TableColumn<?, ?> columnOrdDate;
	@FXML
	TableColumn<?, ?> columnStatus;
	@FXML
	TableColumn<?, ?> columnManager;

	// Netikri duomenys
	static double productAmount = 22;
	static double productPrice = 50;
	static String productSupplier = "GPS";

	static String clientName = "Dovydas";
	static String clientPhoneNumber = "+3707070707";

	static String orderOrderDecsription = "Aprašymas";
	static String orderArrivalDate = "2018";

	public static void setFormValues() {

		Main.getClient().setName(clientName);
		Main.getClient().setPhoneNumber(clientPhoneNumber);

		Main.getOrders().setDescriptionOfOrder(orderOrderDecsription);
		Main.getOrders().setDeliveryDate(orderArrivalDate);
		Main.getOrders().setManager("Jonas");

		Main.getProduct().setAmount(productAmount);
		Main.getProduct().setPrice(productPrice);
		Main.getProduct().setSupplier(productSupplier);

		Main.getContext().registerShutdownHook();
	}

	private void setCellInfoTable() {

		columnId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		columnDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionOfOrder"));
		columnPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		columnClientName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		columnSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
		columnDevDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
		columnOrdDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnManager.setCellValueFactory(new PropertyValueFactory<>("manager"));
	}

}
