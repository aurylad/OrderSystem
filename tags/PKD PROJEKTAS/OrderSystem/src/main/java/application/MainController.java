package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import beans.BeansInitialization;
import database.table.manager.TableManager;
import databse.tables.Orders;

import databse.tables.Supplier;
import discount.calculator.Discount;
import discount.calculator.DiscountFactory;
import discount.calculator.DiscountRecipient;
import discount.calculator.SilverClient;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import orders.reminder.DateCalculator;
import orders.reminder.RedButtonDecorator;
import orders.reminder.SimpleButton;

public class MainController implements Initializable {

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
	private static Button btnPendingOrders;
	
	@FXML
	private static Button atidaryti;

	static Stage stage;
	private int idNumberForRemove;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableManager.getDataFromDatabase();
		setCellInfoTable();
		setCellSupplierTable();
		setValueToSupplierTextFields();
		annotationBeanImpl();
		//atidaryti.setText("FUCK YOU");
		discountCalculator();
		ordersReminder();
		DateCalculator performanceStage = DateCalculator.getInstance();
		System.out.println(performanceStage.makePendingOrdersList());

		// Šis selectionModel naudojamas įrašo ištrinimui, jog pažymėjus eilutę
		// lentelėje, ji būtų ištrinta
		tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			Orders ordersList = tableOrders.getItems().get(tableOrders.getSelectionModel().getSelectedIndex());
			idNumberForRemove = ordersList.getOrderId();

		});

	}

	private void annotationBeanImpl() {
		Main.getSupplierObj().setCompanyName("Annotation Bean Testing");
		System.out.println(Main.getSupplierObj().getCompanyName());
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

		tableOrders.setItems(TableManager.getOrdersObservableList());
	}

	public void setCellSupplierTable() {

		columnCompanyCode.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
		columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
		columnAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		columnPersonName.setCellValueFactory(new PropertyValueFactory<>("person"));

		tableSupplier.setItems(TableManager.getSupplierObservableList());
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

	// paspaudus mygtuką "naujas" atidaromas naujas langas, forma įrašui pridėti
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

	public static void closeScene() {
		stage.close();
	}

	// atliekama įrašų paieška lentelėje, pagal kelis lentelės laukus, įrašai
	// ieškomi jau užpildytame observableListe, tuomet rasti įrašai sudedami į
	// filteredLista ir atvaizduojamas šis listas, ištrynus paieškos raktažodžius
	// gražinamas senas listas
	@FXML
	public void searchRecord(KeyEvent ke) {
		FilteredList<Orders> filterData = new FilteredList<>(TableManager.getOrdersObservableList(), p -> true);
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

	// panaudotas SceneBuilder mygtukui "ištrinti", naudotas
	// setOnAction. Šis metodas nusiunčia idNumber, kuris gaunamas iš
	// selectionModel (aprašytas aukščiau initialize metode), į TableManager klasę,
	// kurioje įvygdomas sql užklausa. Taip pat panaikina įrašą iš lentelės
	public void deleteRecord() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Patvirtinimo pranešimas");
		alert.setHeaderText("Ar tikrai norite ištrinti šį įrašą?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			TableManager.deleteFromOrdersTable(idNumberForRemove);
			Orders selectedItem = tableOrders.getSelectionModel().getSelectedItem();
			tableOrders.getItems().remove(selectedItem);
		}

	}

	public void refreshData() {
		tableOrders.getItems().clear();
		TableManager.getDataFromDatabase();

	}

	public void discountCalculator() {
		DiscountFactory discountFactory = new DiscountFactory();
		Discount discount = discountFactory.getClientType("GOLDCLIENT");

		System.out.println(discount.calculateDiscount());
	}

	public void ordersReminder() {
		DateCalculator.makePendingOrdersList();
	}

	public static Button getBtnPendingOrders() {
		return btnPendingOrders;
	}

	// public void autowireAnnotation() {
	// AbstractApplicationContext context = new
	// ClassPathXmlApplicationContext("beans/Beans.xml");
	// Product productObhect = (Product) context.getBean("client");
	//
	// System.out.println(productObhect.getAmount() +" "+ productObhect.getPrice()
	// +" "+ productObhect.getSupplier());
	//
	// Supplier supplierObject = productObhect.getSupplierObj();
	// System.out.println(supplierObject.getAddress() +" "+
	// supplierObject.getCompanyName() +"...");
	// }
	
	

}
