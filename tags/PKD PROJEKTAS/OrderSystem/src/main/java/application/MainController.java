package application;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.BeanAnnotation;
import database.table.manager.TableManager;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableManager.getDataFromDatabase();
		setCellInfoTable();
		setCellSupplierTable();
		setValueToSupplierTextFields();
		annotationBeanImpl();

	}

	private void annotationBeanImpl() {

		ApplicationContext context = new AnnotationConfigApplicationContext(BeanAnnotation.class);

		Supplier supplierObj = (Supplier) context.getBean("supplierBean");
//		supplierObj.setAddress("annotation");
//		System.out.println(supplierObj.getAddress());

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

	private void setCellSupplierTable() {

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
	// alert.setHeaderText("Ar tikrai norite atnaujinti šį įrašą?");
	// Optional<ButtonType> result = alert.showAndWait();
	//
	// if (result.get() == ButtonType.OK) {
	//
	// }
	// }
	//
	// }

	public void setAddScene() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/css/files/AddOrderWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Naujas užsakymas");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

}
