package databse.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
public class Product {
	
	private Integer productID;
	private double amount;
	private double price;
	private String supplier;
	

	

//	public Product(int amount, double price, String supplier) {
//		this.amount = amount;
//		this.price = price;
//		this.supplier = supplier;
//	}

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

}
