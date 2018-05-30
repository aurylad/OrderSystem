package database.table.manager;

import javax.persistence.Persistence;

import org.springframework.dao.DataAccessException;

import application.Main;
import databse.tables.Orders;

public class InsertData extends DatabaseManager {

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

		Orders ordersObjectForDb = new Orders();

		ordersObjectForDb.setDescriptionOfOrder(Main.getOrdersBeanObj().getDescriptionOfOrder());
		ordersObjectForDb.setOrder_phoneNumber(Main.getOrdersBeanObj().getOrder_phoneNumber());
		ordersObjectForDb.setOrder_name(Main.getOrdersBeanObj().getOrder_name());
		ordersObjectForDb.setOrder_amount(Main.getOrdersBeanObj().getOrder_amount());
		ordersObjectForDb.setOrder_price(Main.getOrdersBeanObj().getOrder_price());
		ordersObjectForDb.setOrder_supplier(Main.getOrdersBeanObj().getOrder_supplier());
		ordersObjectForDb.setDeliveryDate(Main.getOrdersBeanObj().getDeliveryDate());
		ordersObjectForDb.setStatus(Main.getOrdersBeanObj().getStatus());
		ordersObjectForDb.setManager(Main.getOrdersBeanObj().getManager());

		entityManager.persist(ordersObjectForDb);

		// kolekcijos papildymas naujais nariais
		ordersObservableList.add(ordersObjectForDb);
	}

	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
