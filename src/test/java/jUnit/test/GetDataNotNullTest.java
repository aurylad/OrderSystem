package jUnit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.Test;

import database.table.manager.DatabaseManager;
import database.table.manager.GetData;
import databse.tables.Orders;
import javafx.collections.ObservableList;

class GetDataNotNullTest {

	ObservableList<Orders> check;
	
	@Test
	void check_if_dataList_is_not_empty_after_query() {
		DatabaseManager getData = new GetData();
		getData.execute();
		check = DatabaseManager.getOrdersObservableList();
		assertNotNull(check);
	}

	@Test
	void check_if_dataList_is_empty () {
		assertNull(check);
	}

}
