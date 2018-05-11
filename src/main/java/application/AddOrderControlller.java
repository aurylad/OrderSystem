package application;

import java.net.URL;
import java.util.ResourceBundle;

import database.table.manager.TableManager;
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

	// @FXML
	// private ChoiceBox<String> choiceBoxStatus;
	// @FXML
	// private ChoiceBox<String> managerBoxStatus;
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
		
		System.out.println(TableManager.getSupplierObservableList());
		
	}

}
