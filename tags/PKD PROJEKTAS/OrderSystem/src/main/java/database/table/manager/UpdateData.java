package database.table.manager;

import javax.persistence.Persistence;

import org.springframework.dao.DataAccessException;

import application.UpdateOrderController;

public class UpdateData extends DatabaseManager {

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

	@Override
	void startExecute() {
		entityManager.merge(UpdateOrderController.getOrdersUpdateObj());
	}

	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
