package database.table.manager;

import javax.persistence.Persistence;

import application.Main;
import application.MainController;
import databse.tables.Orders;

public class InsertData extends DatabaseManager {

	@Override
	void initialize() {
		factory = Persistence.createEntityManagerFactory("OrderDb");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

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

		try {
			// lentelės atnaujinimas, kai observable listas pasipildo nauju įrašu
			//MainController.setCellSupplierTable();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
