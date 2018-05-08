package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Client;
import databse.tables.Orders;
import databse.tables.Product;
import databse.tables.Supplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {

	// Table Employees
	@FXML
	private TableView<Orders> tableOrders;
	@FXML
	private TableColumn<?, ?> columnId;
	@FXML
	private TableColumn<?, ?> columnDescription;
	@FXML
	private TableColumn<?, ?> columnPhone;
	@FXML
	private TableColumn<?, ?> columnClientName;
	@FXML
	private TableColumn<?, ?> columnAmount;
	@FXML
	private TableColumn<?, ?> columnPrice;
	@FXML
	private TableColumn<?, ?> columnSupplier;
	@FXML
	private TableColumn<?, ?> columnDevDate;
	@FXML
	private TableColumn<?, ?> columnOrdDate;
	@FXML
	private TableColumn<?, ?> columnStatus;
	@FXML
	private TableColumn<?, ?> columnManager;
	

	
	@FXML
	private TableColumn<?, ?> columnCompanyCode;
	@FXML
	private TableColumn<?, ?> columnCompanyName;
	@FXML
	private TableColumn<?, ?> columnCountry;
	@FXML
	private TableColumn<?, ?> columnAdress;
	@FXML
	private TableColumn<?, ?> columnPhoneNumber;
	@FXML
	private TableColumn<?, ?> columnPersonName;
	@FXML
	private TableView<Supplier> tableSupplier;
	  
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableManager.querySelect();
		setCellInfoTable();
		setCellSupplierTable();
	}
	
	
	// Netikri duomenys
//	static double productAmount = 22;
//	static double productPrice = 50;
//	static String productSupplier = "GPS";
//
//	static String clientName = "Dovydas";
//	static String clientPhoneNumber = "+3707070707";
//
//	static String orderOrderDecsription = "Aprašymas";
//	static String orderArrivalDate = "2018";
//
//	public static void setFormValues() {
//
//		Main.getClient().setName(clientName);
//		Main.getClient().setPhoneNumber(clientPhoneNumber);
//
//		Main.getOrders().setDescriptionOfOrder(orderOrderDecsription);
//		Main.getOrders().setDeliveryDate(orderArrivalDate);
//		Main.getOrders().setManager("Jonas");
//
//		Main.getProduct().setAmount(productAmount);
//		Main.getProduct().setPrice(productPrice);
//		Main.getProduct().setSupplier(productSupplier);
//
//		Main.getContext().registerShutdownHook();
//	}

	private void setCellInfoTable() {

		columnId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		columnDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionOfOrder"));
		columnPhone.setCellValueFactory(new PropertyValueFactory<>("order_phoneNumber"));
		columnClientName.setCellValueFactory(new PropertyValueFactory<>("order_name"));
		columnAmount.setCellValueFactory(new PropertyValueFactory<>("order_amount"));
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("order_price"));
		columnSupplier.setCellValueFactory(new PropertyValueFactory<>("order_supplier"));
		columnDevDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
		columnOrdDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnManager.setCellValueFactory(new PropertyValueFactory<>("manager"));
		
		tableOrders.setItems(TableManager.getOrdersObservableList());
	}
	
	
	private void setCellSupplierTable() {

		columnCompanyCode.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
		columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
		columnAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		columnPersonName.setCellValueFactory(new PropertyValueFactory<>("person"));
		
		tableSupplier.setItems(TableManager.getSupplierObservableList());
	}
	
	

}
