package application;

import java.net.URL;
import java.util.ResourceBundle;

import database.table.manager.DatabaseManager;
import database.table.manager.InsertData;
import database.table.manager.UpdateData;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateOrderController extends MainController implements Initializable {
	@FXML
	private TextField txtFieldManager;

	@FXML
	private TextField txtFieldSupllier;

	@FXML
	private TextField txtFieldPhone;

	@FXML
	private TextField txtFieldBuyer;

	@FXML
	private TextField txtFieldDevDate;

	@FXML
	private TextArea txtFieldDescription;

	@FXML
	private TextField txtFieldPrice;

	@FXML
	private TextField txtFieldAmount;

	@FXML
	private TextField txtFieldStatus;

	static Orders ordersUpdateObj;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setValueToUpdateTextFieldss();
	}

	private void setValueToUpdateTextFieldss() {

		txtFieldManager.setText(Main.getOrdersBeanObj().getManager());
		txtFieldSupllier.setText(Main.getOrdersBeanObj().getOrder_supplier());
		txtFieldPhone.setText(Main.getOrdersBeanObj().getOrder_phoneNumber());
		txtFieldBuyer.setText(Main.getOrdersBeanObj().getOrder_name());
		txtFieldDevDate.setText(Main.getOrdersBeanObj().getDeliveryDate());
		txtFieldDescription.setText(Main.getOrdersBeanObj().getDescriptionOfOrder());
		txtFieldPrice.setText("" + Main.getOrdersBeanObj().getOrder_price());
		txtFieldAmount.setText("" + Main.getOrdersBeanObj().getOrder_amount());
		txtFieldStatus.setText(Main.getOrdersBeanObj().getStatus());
	}

	public void updateOrderRecord() {
		Orders ordersUpdateObj = new Orders();
		ordersUpdateObj.setManager(txtFieldManager.getText());
		ordersUpdateObj.setOrder_supplier(txtFieldSupllier.getText());
		ordersUpdateObj.setOrder_phoneNumber(txtFieldPhone.getText());
		ordersUpdateObj.setOrder_name(txtFieldBuyer.getText());
		ordersUpdateObj.setDeliveryDate(txtFieldDevDate.getText());
		ordersUpdateObj.setDescriptionOfOrder(txtFieldDescription.getText());
		ordersUpdateObj.setOrder_price(Double.parseDouble(txtFieldPrice.getText()));
		ordersUpdateObj.setOrder_amount(Double.parseDouble(txtFieldAmount.getText()));
		ordersUpdateObj.setStatus(txtFieldStatus.getText());
		ordersUpdateObj.setOrderId(Main.getOrdersBeanObj().getOrderId());
		setOrdersUpdateObj(ordersUpdateObj);
		
		DatabaseManager updateData = new UpdateData();
		updateData.execute();
	}

	public static Orders getOrdersUpdateObj() {
		return ordersUpdateObj;
	}

	public void setOrdersUpdateObj(Orders ordersUpdateObj) {
		this.ordersUpdateObj = ordersUpdateObj;
	}

}
