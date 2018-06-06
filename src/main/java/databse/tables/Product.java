package databse.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	private Integer productID;
	private double amount;
	private double price;
	private String supplier;
	
	
	

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProductID() {
		return productID;
	}

	/**
	 * @param productID
	 */
	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	/**
	 * @return
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

//	private Supplier supplierObj;
//	
//	//Naudojamas autowire annotation
//	public Supplier getSupplierObj() {
//		return supplierObj;
//	}
//	
//	@Autowired(required=false)
//	public void setSupplierObj(Supplier supplierObj) {
//		this.supplierObj = supplierObj;
//	}
	
	

}
