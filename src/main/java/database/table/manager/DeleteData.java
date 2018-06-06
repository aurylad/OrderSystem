package database.table.manager;

import javax.persistence.Persistence;

import org.springframework.dao.DataAccessException;

import application.MainController;
import databse.tables.Orders;

public class DeleteData extends DatabaseManager {

	@Override
	void initialize() {
		try {
			factory = Persistence.createEntityManagerFactory("OrderDb");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
		} catch (DataAccessException e) {
			e.getMessage();
			System.out.println("Duomenų bazė neprieinama");
		}
	}

	/* (non-Javadoc)
	 * @see database.table.manager.DatabaseManager#startExecute()
	 */
	@Override
	void startExecute() {
		Orders reference = entityManager.getReference(Orders.class, MainController.getIdNumberForRemove());
		entityManager.remove(reference);
	}

	/* (non-Javadoc)
	 * @see database.table.manager.DatabaseManager#end()
	 */
	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
