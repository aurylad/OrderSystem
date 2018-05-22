package database.table.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import application.AddOrderControlller;
import application.Main;
import application.MainController;
import databse.tables.Orders;
import databse.tables.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableManager {

	 static MainController mainController;
	 static EntityManagerFactory factory;
	 static EntityManager entityManager;
	// kai gaunamas atsakymas iš db, visi duomenys patalpinami į lista
	  static List<Orders> ordersList;
	  static List<Supplier> supplierList;

//	// duomenys iš listo sudedami į observablelista, kuriuo užpildoma lentelė
	 static ObservableList<Orders> ordersObservableList = FXCollections.observableArrayList();
	 static ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

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

	// sudaromas obijektas, kurio reikšmėmis naudojantis bus įtrauktas naujas įrašas
	// duomenų bazėje, naudojamas sudėtinis bean, todėl visa info bus išskaidyta
	// skirtingoms db lentelėms, automatiškai
	public static void insertToOrdersTable() { 
		begin();

		AddOrderControlller ord = new AddOrderControlller();
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
		end();

		// kolekcijos papildymas naujais nariais
		ordersObservableList.add(ordersObjectForDb);
		

		try {
			// lentelės atnaujinimas, kai observable listas pasipildo nauju įrašu
			mainController.setCellSupplierTable();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Ištrinamas įrašas iš visų lentelių, kuriuose id = idNum, idNum gaunamas iš
	// MainController klasės nes tik toje klasėje galima naudoti selectionModel
	// funkciją
	public static void deleteFromOrdersTable(int idNum) {
		begin();
		Orders reference = entityManager.getReference(Orders.class, idNum);
		entityManager.remove(reference);
		end();
	}

	public static void getDataFromDatabase() {

		begin();
//		performanceStage.turnOnLights();
//		System.out.println(performanceStage.getCouner());
		
		// gaunamas listas su domenimis iš duomenų bzės, naudojamas užpildyti tiekėjų
		// lentelę
		String jpql = "Select a From Supplier a";
		Query query = entityManager.createQuery(jpql);
		supplierList = query.getResultList();
		for (Supplier e : supplierList) {
			supplierObservableList.add(e);
		}
		setSupplierObservableList(supplierObservableList);

		// gaunamas listas su domenimis iš duomenų bzės, naudojamas užpildyti užsakymų
		// lentelę
		String jpql2 = "Select a From Orders a";
		Query query2 = entityManager.createQuery(jpql2);
		ordersList = query2.getResultList();
		for (Orders e : ordersList) {
			ordersObservableList.add(e);
		}
		setOrdersObservableList(ordersObservableList);

		end();
	}

	// private static void remove() {
	// Supplier reference = entityManager.getReference(Supplier.class, 100002);
	// entityManager.remove(reference);
	// }
	//
	// private static void update() {
	// Supplier existSupplier = new Supplier();
	// existSupplier.setCompanyName("Atnaujinimas");
	// existSupplier.setAddress("Adresas");
	// existSupplier.setCompanyCode(100002);
	// existSupplier.setCountry("Salis");
	// existSupplier.setPerson("Asmuo");
	// existSupplier.setPhoneNumber("+3000000000");
	//
	// entityManager.merge(existSupplier);
	// }
	//
	// private static void find() {
	// Supplier findSupplier = entityManager.find(Supplier.class, 100002);
	// System.out.println(findSupplier.getCompanyName());
	// }

	// ------------------------------------------SETERS AND
	// GETERS-------------------------------------------//

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

	public static List<Orders> getOrdersList() {
		return ordersList;
	}

}
