package database.table.manager;

import javax.persistence.Persistence;

import application.MainController;
import databse.tables.Orders;

public class DeleteData extends DatabaseManager {

	@Override
	void initialize() {
		factory = Persistence.createEntityManagerFactory("OrderDb");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Override
	void startExecute() {
		Orders reference = entityManager.getReference(Orders.class, MainController.getIdNumberForRemove());
		entityManager.remove(reference);
	}

	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
