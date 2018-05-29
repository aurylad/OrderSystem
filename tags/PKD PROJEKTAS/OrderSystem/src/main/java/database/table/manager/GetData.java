package database.table.manager;

import javax.persistence.Persistence;
import javax.persistence.Query;

import databse.tables.Orders;
import databse.tables.Supplier;

public class GetData extends DatabaseManager {

	// -----------------TEMPLATE PATTERN (BEHAVIORAL)-------------------//

	@Override
	void initialize() {
		factory = Persistence.createEntityManagerFactory("OrderDb");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

	}

	@Override
	void startExecute() {
		// gaunamas listas su domenimis iš duomenų bzės, naudojamas užpildyti tiekėjų
		// lentelę
		String jpql = "Select a From Supplier a";
		Query query = entityManager.createQuery(jpql);
		supplierList = query.getResultList();
		for (Supplier e : supplierList) {
			supplierObservableList.add(e);
		}

		// gaunamas listas su domenimis iš duomenų bzės, naudojamas užpildyti užsakymų
		// lentelę
		String jpql2 = "Select a From Orders a";
		Query query2 = entityManager.createQuery(jpql2);
		ordersList = query2.getResultList();
		for (Orders e : ordersList) {
			ordersObservableList.add(e);
		}

	}

	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
