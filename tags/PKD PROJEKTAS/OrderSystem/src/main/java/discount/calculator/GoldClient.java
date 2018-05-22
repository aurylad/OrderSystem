package discount.calculator;

import java.util.HashSet;
import java.util.Set;

import database.table.manager.TableManager;
import databse.tables.Orders;

public class GoldClient implements Discount {
	
	private String phoneNumber;
	Set<String> s = new HashSet<>();
	DiscountRecipient recipient;
	int counter = 0;
	
	//For Factory Pattern
	@Override
	public void calculateDiscount() {
		for (Orders orders : TableManager.getOrdersList()) {
			if (s.add(orders.getOrder_phoneNumber()) == false) {
				 recipient = new DiscountRecipient.Builder()
						.phone(orders.getOrder_phoneNumber())
						.build();
				 counter++;
			}
		
		}
		System.out.println(DiscountRecipient.getList());
		System.out.println(counter);
	}

}
