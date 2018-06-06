package application;

import java.io.IOException;
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
import discount.calculator.DiscountRecipient;
import discount.calculator.GoldClient;
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
	static DateCalculator performanceStage = DateCalculator.getInstance();

	@FXML
	protected TableView<Orders> tableOrders;
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

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Gaunama is DatabaseManager obijekto
		setValueToUpdateTextFields();
		getData.execute();

		setCellInfoTable();
		setCellSupplierTable();
		setValueToSupplierTextFields();

		annotationBeanImpl();

		// sudaro sarasa klientu, kurie gauna nuolaida
		//discountCalculator();

		// Pakeicia mygtuko spalva, pagal irasus lenteleje
		performanceStage.makePendingOrdersList();
		checkColor();

		// Sis selectionModel naudojamas iraso istrinimui, jog pazymėjus eilute
		// lenteleje, butu gautas iraso ID
		tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			Orders ordersList = tableOrders.getItems().get(tableOrders.getSelectionModel().getSelectedIndex());
			idNumberForRemove = ordersList.getOrderId();
		});

	}

	/**
	 * 
	 */
	private void annotationBeanImpl() {
		Main.getSupplierObj().setCompanyName("Annotation Bean Testing");
		Main.getSupplierObj().getCompanyName();
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	public void setCellSupplierTable() {

		columnCompanyCode.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
		columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
		columnAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		columnPersonName.setCellValueFactory(new PropertyValueFactory<>("person"));

		tableSupplier.setItems(DatabaseManager.getSupplierObservableList());
	}

	/**
	 * 
	 */
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

	/**
	 * @throws IOException
	 */
	public void setNewOrderScene() throws IOException {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/AddOrderWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Naujas užsakymas");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
	}

	/**
	 * @throws IOException
	 */
	public void setNotesScene() throws IOException {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/notesWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Užrašai");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
		
	}
	
	/**
	 * @throws IOException
	 */
	public void setUpdateScene() throws IOException {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/UpdateOrderInfoWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Atnaujinti įrašą");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
	}
	
	/**
	 * @throws IOException
	 */
	public void setPendingScene() throws IOException {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/PendingOrderWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Laukiantys užsakymai");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
	}

	public static void closeScene() {
		stage.close();
	}

	// atliekama irasų paieska lenteleje, pagal kelis lenteles laukus, irasai
	// ieskomi jau uzpildytame observableListe, tuomet rasti irasai sudedami i
	// filteredLista ir atvaizduojamas sis listas, istrynus paieskos raktazodzius
	// grazinamas senas listas
	/**
	 * @param ke
	 */
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

	// Istrina pazymeta irasa is lenteles ir iskviecia klase execute() kuri istrina
	// irasa is duomenu bazes
	/**
	 * 
	 */
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

	/**
	 * 
	 */
	public void refreshData() {
		tableOrders.getItems().clear();
		tableSupplier.getItems().clear();
		getData.execute();
		tableOrders.setItems(DatabaseManager.getOrdersObservableList());
	}

	// iskviecia klase, kuri sudaro listą klientu, kurie gauna nuolaida ir lista
	// atvaizduoja lenteleje
	/**
	 * 
	 */
	public void discountCalculator() {
		DiscountFactory discountFactory = new DiscountFactory();
		Discount discount = discountFactory.getClientType("GOLDCLIENT");

		System.out.println(discount.calculateDiscount());
		
	}

	// pakeicia mygtuko spalva i zalia, jei yra uzsakymu kurie turi buti pristatyti
	// siandien, jei tokiu uszakymų nera, tuomet spalva - melyna
	/**
	 * 
	 */
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

	
	/**
	 * 
	 */
	public void setValueToUpdateTextFields(){

		tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			Orders ordersList = tableOrders.getItems().get(tableOrders.getSelectionModel().getSelectedIndex());
			Main.getOrdersBeanObj().setOrderId(ordersList.getOrderId());
			Main.getOrdersBeanObj().setDescriptionOfOrder(ordersList.getDescriptionOfOrder());
			Main.getOrdersBeanObj().setOrder_phoneNumber(ordersList.getOrder_phoneNumber());
			Main.getOrdersBeanObj().setOrder_name(ordersList.getOrder_name());
			Main.getOrdersBeanObj().setOrder_amount(ordersList.getOrder_amount());
			Main.getOrdersBeanObj().setOrder_price(ordersList.getOrder_price());
			Main.getOrdersBeanObj().setOrder_supplier(ordersList.getOrder_supplier());
			Main.getOrdersBeanObj().setDeliveryDate(ordersList.getDeliveryDate());
			Main.getOrdersBeanObj().setStatus(ordersList.getStatus());
			Main.getOrdersBeanObj().setManager(ordersList.getManager());
			
		});	
	}
	
	/**
	 * @throws IOException
	 */
	public void fillDiscountReceiversTable () throws IOException {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/DiscountReceiversWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setTitle("Laukiantys užsakymai");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("nerastas failas, tokiu pavadinimu");
		}
		
	}

	public static DateCalculator getPerformanceStage() {
		return performanceStage;
	}
	
	
	
}
