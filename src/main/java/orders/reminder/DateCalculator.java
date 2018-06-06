package orders.reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import database.table.manager.DatabaseManager;
import databse.tables.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DateCalculator {

	private static DateCalculator INSTANCE = null;
	private static String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	private static ObservableList<Orders> pendingOrderObservableList = FXCollections.observableArrayList();

	// Singleton Patter, obijektas sukuriamas tik vieną kartą
	
	// private konstruktorius neleidžia kitoms klasėms sukurti šios klasės obijekto
	/**
	 * 
	 */
	private DateCalculator() {

	}

	/**
	 * @return
	 */
	public synchronized static DateCalculator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DateCalculator();
		}
		return INSTANCE;
	}

	/**
	 * @return
	 */
	public static ObservableList<Orders> makePendingOrdersList() {
		// Sudaro lista iš užsakymų įrašų, kurie turi būti įvykdyti šiandien
		
		for (Orders orders : DatabaseManager.getOrdersList()) {
			if (orders.getDeliveryDate().equals(currentDate)) {
				pendingOrderObservableList.add(orders);
			}
		}

		return pendingOrderObservableList;
	}

}
