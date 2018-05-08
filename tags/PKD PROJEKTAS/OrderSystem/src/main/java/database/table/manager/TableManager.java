package database.table.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import application.Main;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//static Orders order = new Orders();
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

	
	public static void insertToDatabase() {

		begin();

//		Main.getOrders().setDescriptionOfOrder(Main.getOrders().getDescriptionOfOrder());
//		Main.getOrders().setDeliveryDate(Main.getOrders().getDeliveryDate());
//		Main.getOrders().setManager(Main.getOrders().getManager());
//
//		Main.getClient().setName(Main.getClient().getName());
//		Main.getClient().setPhoneNumber(Main.getClient().getPhoneNumber());
//
//		Main.getProduct().setAmount(Main.getProduct().getAmount());
//		Main.getProduct().setPrice(Main.getProduct().getPrice());
//		Main.getProduct().setSupplier(Main.getProduct().getSupplier());
//
//		entityManager.persist(Main.getOrders());
//		entityManager.persist(Main.getClient());
//		entityManager.persist(Main.getProduct());

		end();
	}


	public static void querySelect() {

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
		
		// Orders orders = new Orders();
		// orders.setDeliveryDate("2020");
		// orders.setDescriptionOfOrder("uzsakymas naujas");
		// orders.setManager("jonas");
		// orders.setOrder_amount(23.0);
		// orders.setOrder_name("Klientas");
		// orders.setOrder_phoneNumber("8654231563");
		// orders.setOrder_price(32.0);
		// orders.setOrder_supplier("Etovis");
		// orders.setStatus("Vykdomas");
		// orders.setOrderDate(date);
		
		// entityManager.persist(orders);

		end();
	}


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

}
