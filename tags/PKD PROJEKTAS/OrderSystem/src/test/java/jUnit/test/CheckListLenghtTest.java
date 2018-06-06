package jUnit.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import database.table.manager.DatabaseManager;
import database.table.manager.GetData;

class CheckListLenghtTest {
	DatabaseManager getData;
	
	@Before
	public void fillListsWithData() {
		getData = new GetData();
		getData.execute();	
	}
	
	@Test
	void check_if_all_list_element_are_added_to_observableList() {
		getData = new GetData();
		getData.execute();	
		int list = DatabaseManager.getOrdersList().size();
		int ordersObservableList = DatabaseManager.getOrdersObservableList().size();
		
		assertEquals(list, ordersObservableList);
	}

}
