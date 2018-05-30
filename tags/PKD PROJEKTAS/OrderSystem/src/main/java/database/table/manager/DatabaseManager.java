package database.table.manager;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class DatabaseManager {

	// -----------------TEMPLATE PATTERN (BEHAVIORAL)-------------------//

	abstract void initialize();

	abstract void startExecute();

	abstract void end();

	static EntityManagerFactory factory;
	static EntityManager entityManager;
	static ObservableList<Orders> ordersObservableList = FXCollections.observableArrayList();
	static ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
	static List<Orders> ordersList;
	static List<Supplier> supplierList;

	// template method
	public final void execute() {

		initialize();

		startExecute();

		end();
	}

	public final void startConnection() {
		initialize();
	}

	public final void workWithData() {
		startExecute();
	}

	public final void endConnection() {
		end();
	}

	public static ObservableList<Orders> getOrdersObservableList() {
		return ordersObservableList;
	}

	public static ObservableList<Supplier> getSupplierObservableList() {
		return supplierObservableList;
	}

	public static List<Orders> getOrdersList() {
		return ordersList;
	}

}
