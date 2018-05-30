package application;

import java.net.URL;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.table.manager.DatabaseManager;
import database.table.manager.InsertData;
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

	private ObservableList<String> statusChoiceBoxList = FXCollections.observableArrayList();
	private ObservableList<String> managerChoiceBoxList = FXCollections.observableArrayList();
	private String date;

	DatabaseManager insertData = new InsertData();

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

	// naudojamas setOnAction, gauna naudotojo įvestus duomenis ir priskiria juos
	// Bean'ams, tuomet iškviečia execute() klasę iš DatabaseManager obijekto
	// (naudojamas Template Patternas)
	public void setNewOrderValuesToBean() {

		if (!(txtFieldDescription.getText().isEmpty())) {
			Main.getOrdersBeanObj().setDescriptionOfOrder(txtFieldDescription.getText());
		}
		if (!(txtFieldPhoneNum.getText().isEmpty())) {
			Main.getOrdersBeanObj().setOrder_phoneNumber(txtFieldPhoneNum.getText());
		}
		if (!(txtFieldClientName.getText().isEmpty())) {
			Main.getOrdersBeanObj().setOrder_name(txtFieldClientName.getText());
		}
		if (!(txtFieldAmount.getText().isEmpty())) {
			try {
				double amount = Double.parseDouble(txtFieldAmount.getText());
				Main.getOrdersBeanObj().setOrder_amount(amount);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Įvesti netinkamo formato duomenys");
			}

		}
		if (!(txtFieldPrice.getText().isEmpty())) {
			try {
				double price = Double.parseDouble(txtFieldPrice.getText());
				Main.getOrdersBeanObj().setOrder_price(price);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Įvesti netinkamo formato duomenys");
			}

		}
		if (!(txtFieldSupplier.getText().isEmpty())) {
			Main.getOrdersBeanObj().setOrder_supplier(txtFieldSupplier.getText());
		}
		if (datePickerDevilery.getValue() != null) {
			date = datePickerDevilery.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			Main.getOrdersBeanObj().setDeliveryDate(date);
		}
		if (!(choiceBoxStatus.getValue().isEmpty())) {
			Main.getOrdersBeanObj().setStatus(choiceBoxStatus.getValue());
		}
		if (!(managerBoxStatus.getValue().isEmpty())) {
			Main.getOrdersBeanObj().setManager(managerBoxStatus.getValue());
		}
		insertData.execute();
		MainController.closeScene();
	}

}
