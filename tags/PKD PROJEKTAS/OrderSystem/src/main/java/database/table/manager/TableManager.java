package database.table.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.dom4j.rule.RuleSet;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import application.Main;
import databse.tables.Client;
import databse.tables.Orders;
import databse.tables.OrdersSingle;
import databse.tables.Product;
import databse.tables.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	//Iniciavimas
	private static void begin() {
		
		try {
			factory = Persistence.createEntityManagerFactory("OrderDb");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	//uzdarymas
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	
	//naujo uzsakymo itraukimas i db
	public static void addAllToDifferentTables() {
		
		begin();

		Main.getOrders().setDescriptionOfOrder(Main.getOrders().getDescriptionOfOrder());
		Main.getOrders().setDeliveryDate(Main.getOrders().getDeliveryDate());
		Main.getOrders().setManager(Main.getOrders().getManager());
		

		Main.getClient().setName(Main.getClient().getName());
		Main.getClient().setPhoneNumber(Main.getClient().getPhoneNumber());

		Main.getProduct().setAmount(Main.getProduct().getAmount());
		Main.getProduct().setPrice(Main.getProduct().getPrice());
		Main.getProduct().setSupplier(Main.getProduct().getSupplier());

		entityManager.persist(Main.getOrders());
		entityManager.persist(Main.getClient());
		entityManager.persist(Main.getProduct());
		
		end();
	}
	
	private static void findValuesFromAllTables() {
		
	}
	

	//CRUD
	private static void create() {
		Supplier supplier = new Supplier();
		supplier.setCompanyName("Testavimas");
		supplier.setAddress("Adresas");
		supplier.setCompanyCode(100002);
		supplier.setCountry("Salis");
		supplier.setPerson("Asmuo");
		supplier.setPhoneNumber("+3000000000");

		entityManager.persist(supplier);
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

	public static void queryInsert () {
		
		begin();
		
		String jpql = "Select a From Orders a";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Orders> resultList = query.getResultList();		
		
		for (Orders order : resultList) {
			System.out.println(dateFormat.format(order.getOrderDate()));
		}
		
		
		
//		Orders orders = new Orders();
//		orders.setDeliveryDate("2020");
//		orders.setDescriptionOfOrder("uzsakymas naujas");
//		orders.setManager("jonas");
//		orders.setOrder_amount(23.0);
//		orders.setOrder_name("Klientas");
//		orders.setOrder_phoneNumber("8654231563");
//		orders.setOrder_price(32.0);
//		orders.setOrder_supplier("Etovis");
//		orders.setStatus("Vykdomas");
//		orders.setOrderDate(date);
//		
//		try {
//			entityManager.persist(orders);
//		} catch (RuntimeException e) {
//			System.out.println(e);
//		}
//		
//		
//		
		end();
	}

	
	private static void remove() {
		Supplier reference = entityManager.getReference(Supplier.class, 100002);
		entityManager.remove(reference);
	}

}
