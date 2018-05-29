package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import database.table.manager.DatabaseManager;
import database.table.manager.DeleteData;
import database.table.manager.GetData;
import databse.tables.Orders;

import databse.tables.Supplier;
import discount.calculator.Discount;
import discount.calculator.DiscountFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import orders.reminder.DateCalculator;
import orders.reminder.RedButtonDecorator;
import orders.reminder.SimpleButton;

public class MainController implements Initializable {

	static Stage stage;
	private static int idNumberForRemove;

	DatabaseManager getData = new GetData();
	DatabaseManager deleteData = new DeleteData();
	DateCalculator performanceStage = DateCalculator.getInstance();

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
	private TableView<Supplier> tableSupplier;
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
	private TextField txtFieldCompanyCode;
	@FXML
	private TextField txtFieldCompanyName;
	@FXML
	private TextField txtFieldCompanyCountry;
	@FXML
	private TextField txtFieldCompanyAdress;
	@FXML
	private TextField txtFieldCompanyPhone;
	@FXML
	private TextField txtFieldCompanyPerson;

	@FXML
	private TextField txtFieldSearch;

	@FXML
	Button btnPendingOrders;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Gaunama iš DatabaseManager obijekto
		getData.execute();

		setCellInfoTable();
		setCellSupplierTable();
		setValueToSupplierTextFields();

		annotationBeanImpl();

		// sudaro sąrašą klientų, kurie gauna nuolaidą
		discountCalculator();

		// Pakeičia mygtuko spalvą, pagal įrašus lentelėje
		performanceStage.makePendingOrdersList();
		checkColor();

		// Šis selectionModel naudojamas įrašo ištrinimui, jog pažymėjus eilutę
		// lentelėje, būtų gautas įrašo ID
		tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			Orders ordersList = tableOrders.getItems().get(tableOrders.getSelectionModel().getSelectedIndex());
			idNumberForRemove = ordersList.getOrderId();
		});

	}

	private void annotationBeanImpl() {
		Main.getSupplierObj().setCompanyName("Annotation Bean Testing");
		Main.getSupplierObj().getCompanyName();
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

		tableOrders.setItems(DatabaseManager.getOrdersObservableList());
	}

	public void setCellSupplierTable() {

		columnCompanyCode.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
		columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
		columnAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		columnPersonName.setCellValueFactory(new PropertyValueFactory<>("person"));

		tableSupplier.setItems(DatabaseManager.getSupplierObservableList());
	}

	private void setValueToSupplierTextFields() {

		tableSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			Supplier supplierList = tableSupplier.getItems().get(tableSupplier.getSelectionModel().getSelectedIndex());
			txtFieldCompanyCode.setText("" + supplierList.getCompanyCode());
			txtFieldCompanyName.setText(supplierList.getCompanyName());
			txtFieldCompanyCountry.setText(supplierList.getCountry());
			txtFieldCompanyAdress.setText(supplierList.getAddress());
			txtFieldCompanyPhone.setText(supplierList.getPhoneNumber());
			txtFieldCompanyPerson.setText(supplierList.getPerson());
		});
	}

	public void setNewOrderScene() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/AddOrderWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Naujas užsakymas");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setNotesScene() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/notesWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Užrašai");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeScene() {
		stage.close();
	}

	// atliekama įrašų paieška lentelėje, pagal kelis lentelės laukus, įrašai
	// ieškomi jau užpildytame observableListe, tuomet rasti įrašai sudedami į
	// filteredLista ir atvaizduojamas šis listas, ištrynus paieškos raktažodžius
	// gražinamas senas listas
	@FXML
	public void searchRecord(KeyEvent ke) {
		FilteredList<Orders> filterData = new FilteredList<>(DatabaseManager.getOrdersObservableList(), p -> true);
		txtFieldSearch.textProperty().addListener((observable, oldvalue, newvalue) -> {
			filterData.setPredicate(tableOrders -> {

				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}

				String typedText = newvalue.toLowerCase();

				if (tableOrders.getManager().toLowerCase().indexOf(typedText) != -1) {
					return true;
				}
				if (tableOrders.getOrder_phoneNumber().toLowerCase().indexOf(typedText) != -1) {
					return true;
				}

				return false;
			});
			SortedList<Orders> sortedList = new SortedList<>(filterData);
			sortedList.comparatorProperty().bind(tableOrders.comparatorProperty());

			tableOrders.setItems(sortedList);

		});
	}

	// Ištrina pažymėtą įrašą iš lentelės ir iškviečia klasę execute() kuri ištrina
	// įrašą iš duomenų bazės
	public void deleteRecord() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Patvirtinimo pranešimas");
		alert.setHeaderText("Ar tikrai norite ištrinti šį įrašą?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Orders selectedItem = tableOrders.getSelectionModel().getSelectedItem();
			tableOrders.getItems().remove(selectedItem);
			deleteData.execute();
		}

	}

	public void refreshData() {
		tableOrders.getItems().clear();
		tableSupplier.getItems().clear();
		getData.execute();
		tableOrders.setItems(DatabaseManager.getOrdersObservableList());
	}

	// iškviečia klasę, kuri sudaro listą klientų, kurie gauna nuolaida ir lista
	// atvaizduoja lentelėje
	public void discountCalculator() {
		DiscountFactory discountFactory = new DiscountFactory();
		Discount discount = discountFactory.getClientType("GOLDCLIENT");

		discount.calculateDiscount();
	}

	// pakeičia mygtuko spalvą į žalią, jei yra užsakymų kurie turi būti pristatyti
	// šiandien, jei tokių užsakymų nėra, tuomet spalva - mėlyna
	public void checkColor() {
		// Naudojamas Decorate Pattern ir Singleton Pattern
		if (performanceStage.makePendingOrdersList().isEmpty()) {
			orders.reminder.Button button = new SimpleButton();
			btnPendingOrders.setStyle(button.setButton());
		} else {
			orders.reminder.Button decoratedButton = new RedButtonDecorator(new SimpleButton());
			btnPendingOrders.setStyle(decoratedButton.setButton());
		}
	}

	public static int getIdNumberForRemove() {
		return idNumberForRemove;
	}

	// public void updateSupplierTable() {
	//
	// if (txtFieldCompanyCode.getText().isEmpty() ||
	// txtFieldCompanyName.getText().isEmpty()
	// || txtFieldCompanyCountry.getText().isEmpty() ||
	// txtFieldCompanyAdress.getText().isEmpty()
	// || txtFieldCompanyPhone.getText().isEmpty() ||
	// txtFieldCompanyPerson.getText().isEmpty()) {
	//
	// Alert alert = new Alert(AlertType.ERROR);
	// alert.setTitle("Klaidos pranešimas");
	// alert.setHeaderText("Rasti tušti laukai!");
	// alert.setContentText("Prašome užpildyti visus laukus");
	// alert.showAndWait();
	//
	// } else {
	//
	// Alert alert = new Alert(AlertType.CONFIRMATION);
	// alert.setTitle("Patvirtinimo pranešimas");
	// alert.setHeaderText("Ar tikrai norite ištrinti šį įrašą?");
	// Optional<ButtonType> result = alert.showAndWait();
	//
	// if (result.get() == ButtonType.OK) {
	//
	// }
	// }
	//
	// }
}
