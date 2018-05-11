package application;

import java.net.URL;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.table.manager.TableManager;
import databse.tables.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddOrderControlller implements Initializable {

	ObservableList<String> statusChoiceBoxList = FXCollections.observableArrayList();
	ObservableList<String> managerChoiceBoxList = FXCollections.observableArrayList();
	String date;
	static Orders ordersBeanObj;
	AbstractApplicationContext context;

	@FXML
	private Button buttonCancel;
	@FXML
	private TextField txtFieldPhoneNum;
	@FXML
	private TextField txtFieldClientName;
	@FXML
	private DatePicker datePickerDevilery;
	@FXML
	private TextArea txtFieldDescription;
	@FXML
	private TextField txtFieldPrice;
	@FXML
	private TextField txtFieldAmount;
	@FXML
	private TextField txtFieldSupplier;
	@FXML
	private ChoiceBox<String> choiceBoxStatus;
	@FXML
	private ChoiceBox<String> managerBoxStatus;
	@FXML
	private Button buttonAdd;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fillChoiceBoxes();

	}

	private void fillChoiceBoxes() {
		choiceBoxStatus.setValue("--Pasirinkti--");
		statusChoiceBoxList.addAll("--Pasirinkti--", "vykdomas", "baigtas", "nutrauktas");
		choiceBoxStatus.getItems().addAll(statusChoiceBoxList);

		managerBoxStatus.setValue("--Pasirinkti--");
		managerChoiceBoxList.addAll("--Pasirinkti--", "Jonas", "Petras", "Antanas");
		managerBoxStatus.getItems().addAll(managerChoiceBoxList);

	}

	public void setNewOrderValues() {

		context = new ClassPathXmlApplicationContext("beans/Beans.xml");

		ordersBeanObj = (Orders) context.getBean("ordersBean");

		if (!(txtFieldDescription.getText().isEmpty())) {
			ordersBeanObj.setDescriptionOfOrder(txtFieldDescription.getText());
		}
		if (!(txtFieldPhoneNum.getText().isEmpty())) {
			ordersBeanObj.setOrder_phoneNumber(txtFieldPhoneNum.getText());
		}
		if (!(txtFieldClientName.getText().isEmpty())) {
			ordersBeanObj.setOrder_name(txtFieldClientName.getText());
		}
		if (!(txtFieldAmount.getText().isEmpty())) {
			double amount = Double.parseDouble(txtFieldAmount.getText());
			ordersBeanObj.setOrder_amount(amount);
		}
		if (!(txtFieldPrice.getText().isEmpty())) {
			double price = Double.parseDouble(txtFieldPrice.getText());
			ordersBeanObj.setOrder_price(price);
		}
		if (!(txtFieldSupplier.getText().isEmpty())) {
			ordersBeanObj.setOrder_supplier(txtFieldSupplier.getText());
		}
		if (datePickerDevilery.getValue() != null) {
			date = datePickerDevilery.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			ordersBeanObj.setDeliveryDate(date);
		}
		if (!(choiceBoxStatus.getValue().isEmpty())) {
			ordersBeanObj.setStatus(choiceBoxStatus.getValue());
		}
		if (!(managerBoxStatus.getValue().isEmpty())) {
			ordersBeanObj.setManager(managerBoxStatus.getValue());
		}
		TableManager.insertToOrdersTable();
	}

	public Orders getOrdersBeanObj() {
		return ordersBeanObj;
	}

	public AbstractApplicationContext getContext() {
		return context;
	}

}
