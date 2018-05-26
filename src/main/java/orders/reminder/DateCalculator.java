package orders.reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import database.table.manager.TableManager;
import databse.tables.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DateCalculator {
	
	private static DateCalculator INSTANCE = null;
	private static String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	private static ObservableList<Orders> pendingOrderObservableList = FXCollections.observableArrayList();
	
	//private konstruktorius neleidžia kitoms klasėms sukurti šios klasės obijekto
	private DateCalculator(){

	}

	public synchronized static DateCalculator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DateCalculator();
		}
		return INSTANCE;
	}
	
	public static ObservableList<Orders> makePendingOrdersList() {
		for (Orders orders : TableManager.getOrdersList()) {
			if(orders.getDeliveryDate().equals(currentDate)) {
				pendingOrderObservableList.add(orders);
			}
		}
		return pendingOrderObservableList;
	}
	
	
	
}
