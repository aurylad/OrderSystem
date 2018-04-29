package database.table.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import application.Main;
import databse.tables.Client;
import databse.tables.Orders;
import databse.tables.Product;
import databse.tables.Supplier;

public class TableManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;
	
	
	//Iniciavimas
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("OrderDb");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
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

	
	private static void query() {
		String jpql = "Select b From Supplier b";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Supplier> resultList = query.getResultList();

		for (Supplier supll : resultList) {
			System.out.println(supll.getCompanyName());
		}
	}

	
	private static void remove() {
		Supplier reference = entityManager.getReference(Supplier.class, 100002);
		entityManager.remove(reference);
	}

}
