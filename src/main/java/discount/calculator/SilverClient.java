package discount.calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import database.table.manager.DatabaseManager;
import databse.tables.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SilverClient implements Discount {
	DiscountRecipient recipient;
	static ObservableList<DiscountRecipient> silverClientObservableList = FXCollections.observableArrayList();
	ArrayList <String> phoneList = new ArrayList<String>();
	Set<String> uniquePhone;
	Set<String> unique = new HashSet<>();
		
	//Factory Pattern
	/* (non-Javadoc)
	 * @see discount.calculator.Discount#calculateDiscount()
	 */
	@Override
	public ObservableList<DiscountRecipient> calculateDiscount() {
		for (Orders orders : DatabaseManager.getOrdersList()) {
			if (unique.add(orders.getOrder_phoneNumber()) == false) {
				phoneList.add(orders.getOrder_phoneNumber());
			}
		}
		
		uniquePhone = new HashSet<String>(phoneList);
		
		for(String temp : uniquePhone) {
			//Builder Pattern
			recipient = new DiscountRecipient.Builder()
			.phone(temp)
			.dicount(10)
			.build();
			silverClientObservableList.add(recipient);
		}
		
		//Kolekcijos rusiavimas (.sorted())
		return silverClientObservableList.sorted();
	}


}
