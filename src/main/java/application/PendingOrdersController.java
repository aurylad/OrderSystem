package application;

import java.net.URL;
import java.util.ResourceBundle;

import databse.tables.Orders;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import orders.reminder.DateCalculator;

public class PendingOrdersController implements Initializable {
	@FXML
	private TableColumn<?, ?> columnClientName;

	@FXML
	private TableColumn<?, ?> columnOrdDate;

	@FXML
	private TableColumn<?, ?> columnManager;

	@FXML
	private TableColumn<?, ?> columnAmount;

	@FXML
	private TableColumn<?, ?> columnId;

	@FXML
	private TableColumn<?, ?> columnStatus;

	@FXML
	private TableColumn<?, ?> columnPrice;

	@FXML
	private TableColumn<?, ?> columnDescription;

	@FXML
	private TableView<Orders> tableOrders;

	@FXML
	private TableColumn<?, ?> columnSupplier;

	@FXML
	private TableColumn<?, ?> columnPhone;

	@FXML
	private TableColumn<?, ?> columnDevDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCellInfoTable();
		
	}
	
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

		tableOrders.setItems(DateCalculator.makePendingOrdersList());
		tableOrders.getItems().clear();
		System.out.println(DateCalculator.makePendingOrdersList());
	}
}
