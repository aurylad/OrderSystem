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

	// Singleton Patter, obijektas sukuriamas tik vieną kartą
	// private konstruktorius neleidžia kitoms klasėms sukurti šios klasės obijekto
	private DateCalculator() {

	}

	public synchronized static DateCalculator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DateCalculator();
		}
		return INSTANCE;
	}

	public static ObservableList<Orders> makePendingOrdersList() {
		// Sudaro lista iš užsakymų įrašų, kurie turi būti įvykdyti šiandien
		for (Orders orders : TableManager.getOrdersList()) {
			if (orders.getDeliveryDate().equals(currentDate)) {
				pendingOrderObservableList.add(orders);
			}
		}

		// Decorator Pattern, naudojamas mygtuko spalvai pakeisti, spalva pakeičiama jei
		// pendingOrderObservableList nėra tuščias
		if (pendingOrderObservableList.isEmpty()) {
			System.out.println("lygus nuliuo");
			Button button = new SimpleButton();
			button.setButton();
		} else {
			System.out.println("nelygus nuliui");
			Button decoratedButton = new RedButtonDecorator(new SimpleButton());
			decoratedButton.setButton();
		}
		return pendingOrderObservableList;
	}

}
