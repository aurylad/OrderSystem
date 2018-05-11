package database.table.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import application.AddOrderControlller;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TableManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static List<Orders> ordersList;
	private static ObservableList<Orders> ordersObservableList = FXCollections.observableArrayList();

	private static List<Supplier> supplierList;
	private static ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

	private static void begin() {
		try {
			factory = Persistence.createEntityManagerFactory("OrderDb");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	public static void insertToOrdersTable() {

		begin();
		
		AddOrderControlller ord = new AddOrderControlller();
		Orders orders = new Orders();
		
		orders.setDescriptionOfOrder(ord.getOrdersBeanObj().getDescriptionOfOrder());
		orders.setOrder_phoneNumber(ord.getOrdersBeanObj().getOrder_phoneNumber());
		orders.setOrder_name(ord.getOrdersBeanObj().getOrder_name());
		orders.setOrder_amount(ord.getOrdersBeanObj().getOrder_amount());
		orders.setOrder_price(ord.getOrdersBeanObj().getOrder_price());
		orders.setOrder_supplier(ord.getOrdersBeanObj().getOrder_supplier());
		orders.setDeliveryDate(ord.getOrdersBeanObj().getDeliveryDate());
		orders.setStatus(ord.getOrdersBeanObj().getStatus());
		orders.setManager(ord.getOrdersBeanObj().getManager());
		
		System.out.println("Veikia");
		entityManager.persist(orders);
		System.out.println("Nebeveikia");
		
		end();

	}

	public static void getDataFromDatabase() {

		begin();

		String jpql = "Select a From Supplier a";
		Query query = entityManager.createQuery(jpql);
		supplierList = query.getResultList();
		for (Supplier e : supplierList) {
			supplierObservableList.add(e);
		}
		setSupplierObservableList(supplierObservableList);

		String jpql2 = "Select a From Orders a";
		Query query2 = entityManager.createQuery(jpql2);
		ordersList = query2.getResultList();
		for (Orders e : ordersList) {
			ordersObservableList.add(e);
		}
		setOrdersObservableList(ordersObservableList);

		end();
	}

	private static void remove() {
		Supplier reference = entityManager.getReference(Supplier.class, 100002);
		entityManager.remove(reference);
	}

	private static void update() {
		Supplier existSupplier = new Supplier();
		existSupplier.setCompanyName("Atnaujinimas");
		existSupplier.setAddress("Adresas");
		existSupplier.setCompanyCode(100002);
		existSupplier.setCountry("Salis");
		existSupplier.setPerson("Asmuo");
		existSupplier.setPhoneNumber("+3000000000");

		entityManager.merge(existSupplier);
	}

	private static void find() {
		Supplier findSupplier = entityManager.find(Supplier.class, 100002);
		System.out.println(findSupplier.getCompanyName());
	}

	//------------------------------------------SETERS AND GETERS-------------------------------------------//
	
	public static ObservableList<Orders> getOrdersObservableList() {
		return ordersObservableList;
	}

	public static void setOrdersObservableList(ObservableList<Orders> ordersObservableList) {
		TableManager.ordersObservableList = ordersObservableList;
	}

	public static ObservableList<Supplier> getSupplierObservableList() {
		return supplierObservableList;
	}

	public static void setSupplierObservableList(ObservableList<Supplier> supplierObservableList) {
		TableManager.supplierObservableList = supplierObservableList;
	}

}
