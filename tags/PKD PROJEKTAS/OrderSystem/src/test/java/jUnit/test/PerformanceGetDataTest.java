package jUnit.test;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import database.table.manager.DatabaseManager;
import database.table.manager.GetData;

class PerformanceGetDataTest {

	DatabaseManager getData = new GetData();;

	@BeforeClass
	public void runOnceBeforeClass() {
		System.out.println("before");
		getData.startConnection();
	}

	@Test
	void connection_and_data_retrieving_speed_test() {
		System.out.println("test");
		assertTimeout(Duration.ofMillis(2000), () -> getData.workWithData());
	}

	@AfterClass
	public void runOnceAfterClass() {
		System.out.println("after");
		getData.endConnection();
	}

}
