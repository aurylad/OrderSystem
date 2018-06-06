package application;

import java.net.URL;
import java.util.ResourceBundle;

import discount.calculator.Discount;
import discount.calculator.DiscountFactory;
import discount.calculator.DiscountRecipient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DiscountReceiverController implements Initializable {
	@FXML
	private TableView<DiscountRecipient> tableDiscount;

	@FXML
	private TableColumn<?, ?> columnDiscount;

	@FXML
	private TableColumn<?, ?> columnPhoneID;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		DiscountFactory discountFactory = new DiscountFactory();
		Discount discount = discountFactory.getClientType("GOLDCLIENT");
		columnDiscount.setCellValueFactory(new PropertyValueFactory<>("dicount"));
		columnPhoneID.setCellValueFactory(new PropertyValueFactory<>("phone"));

		tableDiscount.setItems(discount.calculateDiscount());
	}

}
