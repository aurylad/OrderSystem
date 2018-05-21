package databse.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Product {

	private Integer productID;
	private double amount;
	private double price;
	private String supplier;
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

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
